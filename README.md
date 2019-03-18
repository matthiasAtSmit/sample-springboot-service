## Springboot Service ein Beispiel 

### Deployment Umgebung 
   - Bitbucket als Quellcodeverwaltung
   - Jenkins als Continuous Integration Server
   - SonarQube zur statischen Code-Analyse
   - Openshift als Container Platform 
   - Docker zum bereitstellen der Container
   
### Deployment Prozess
   - Eingeplant
   - Entwicklung
   - Codereview
   - Test
   - Ak-Test
   - Fertig
        
### Inhalt
   - Api.yaml
     - Ein Endpunkt zum abfragen einer Begrüßung
   - Quellcode der Springboot Anwendung
   - pom.xml
     - Abhängigkeiten
     - Generierung des Quellcodes für die Api beim Kompilieren
   - Jenkinsfile
   - Dockerfile
   
### Deployment im Openshift
##### Erstellen von DeploymentConfig, Service, ReplicationController und der Route
oc create -f openshift/deployment.yaml

#### Löschen aller Services im Projekt
oc delete all --all

#### Anzeigen aller Pods oder aller Resourcen
oc get po
oc get all
watch oc get all 

