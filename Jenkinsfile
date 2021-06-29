node("master") {
    stage("Build") {
        checkout scm
        sh "ls"
        // unarchive mapping: ['target/': './ORM-testing-1.0.jar']
        copyArtifacts projectName: "testing-gh-webhook", selector: lastCompleted()
        withCredentials([string(credentialsId: 'sonar_auth_token', variable: 'sonar_auth_token')]) {
            println "${sonar_auth_token}"
            sh '''              
              mvn install:install-file \
              -Dfile="ORM-testing-1.0.jar" \
              -DgroupId="org.example" \
              -DartifactId="ORM-testing" \
              -Dversion="1.0" \
              -Dpackaging=jar \
              -DgeneratePom=true'''
            sh "mvn clean package"
            sh "mvn sonar:sonar -Dsonar.login=${sonar_auth_token} -Dsonar.host.url=https://sonarcloud.io -Dsonar.organization=2105-may24-devops -Dsonar.projectKey=2105-may24-devops_project1-team1_teamBWebApp"
        }

        // stash that target/bankapp.war
        sh "ls"
        sh "pwd"
    }
}