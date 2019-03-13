## Springboot Service ein Beispiel 

### Deployment Umgebung kurz beschreiben
   - Bitbucker als Quellcodeverwaltun
   - Jenkins als Continuous Integration Server
   - SonarQube zur statischen Code-Analyse
   - Openshift als Container Platform 
   - Docker zum bereitstellen der Container
   
### Deployment Prozess
   - Eingeplant
   - Entwicklung
   - Codereview
   - Release
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