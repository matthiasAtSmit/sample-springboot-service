pipeline {
    agent {
        node 'maven'
    }

    environment {
        SERVICE_VERSION = VersionNumber(projectStartDate: '2017-01-01', worstResultForIncrement: 'SUCCESS'
            , versionNumberString: '${BUILD_DATE_FORMATTED,"yyyyMMdd"}-r${BUILDS_TODAY, XX}', versionPrefix: '')
        SERVICE_NAME = 'greeting'
        SCM_URL = 'ssh://git@bitbucket-ci-tools.apps.shift.smartinsurtech.de:30999/train/sample-springboot-service.git'
    }

    options {
        buildDiscarder(logRotator(numToKeepStr: '7'))
    }

    stages {
        stage('init') {
            steps {
                echo sh(returnStdout: true, script: 'env')
            }
        }
        stage('display version') {
            when {
                branch 'master'
            }
            steps {
                script {
                    currentBuild.displayName = SERVICE_VERSION
                }
            }
        }
        stage('ssh checkout') {
            steps {
                git(url: SCM_URL, credentialsId: 'jenkins2_ssh_credentials', branch: '$BRANCH_NAME')
            }
        }
        stage('build') {
            steps {
                withMaven(globalMavenSettingsConfig: "d4262a90-c29b-4b7e-9d17-cba6b9a45109") {
                    echo sh(returnStdout: true, script:"mvn help:effective-settings | grep localRepository")
                    sh "mvn versions:set -DnewVersion=$SERVICE_VERSION"
                    sh 'mvn -P docker verify'
                }
            }
        }
        stage('sonarqube analysis') {
            steps {
                withSonarQubeEnv('sonarqube') {
                    withMaven(globalMavenSettingsConfig: "d4262a90-c29b-4b7e-9d17-cba6b9a45109") {
                        sh "mvn -Dsonar.branch=$BRANCH_NAME sonar:sonar"
                    }
                }
            }
        }
        stage("sonarqube quality gate") {
            steps {
                timeout(time: 1, unit: 'HOURS') {
                    // Parameter indicates whether to set pipeline to UNSTABLE if Quality Gate fails
                    // true = set pipeline to UNSTABLE, false = don't
                    // Requires SonarQube Scanner for Jenkins 2.7+
                    waitForQualityGate abortPipeline: true
                }
            }
        }
        stage('create build config and image stream') {
          when {
            allOf {
                branch 'master'
                expression {
                  openshift.withCluster() {
                    return !openshift.selector("bc", "$SERVICE_NAME-bc").exists();
                  }
                }
            }
          }
          steps {
            script {
              openshift.withCluster() {
                openshift.apply(openshift.process( "build-template", "-p", "SERVICE_NAME=$SERVICE_NAME"))
              }
            }
          }
        }
    	stage("build docker image") {
            when {
                branch 'master'
            }
    	    steps {
    		    script {
                 openshift.withCluster() {
                        openshift.withProject() {
                          def builds = openshift.selector("bc", "$SERVICE_NAME-bc").startBuild('--from-dir=./src/main/docker')
                          timeout(2) {
                            builds.untilEach(1) {
                              return (it.object().status.phase == "Complete")
                            }
                          }
                        }
                    }
    		    }
    	    }
    	}
        stage('tag docker image') {
            when {
                branch 'master'
            }
            steps {
                script {
                    openshift.verbose()
                    openshift.withCluster() {
                        openshift.withProject() {
                            openshift.tag("$SERVICE_NAME:latest", "$SERVICE_NAME:$SERVICE_VERSION")
                        }
                    }
                }
            }
        }
        stage('git tag') {
            when {
                branch 'master'
            }
            steps {
                sh("git config user.name 'jenkins'")
                sh("git config user.email 'jenkins2@iwm-software.de'")
                sh "git tag -a $SERVICE_VERSION -m \"Build created\""
                sshagent(['jenkins2_ssh_credentials']) {
                    sh 'git push --tags'
                }
            }
        }
    }

    post {
        always {
            cleanWs()
        }
    }
}
