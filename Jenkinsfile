node("master") {
    stage("Build") {
        checkout scm
        sh "ls"
        unstash 'target/ORM-testing-1.0.jar'
        sh "ls"
    }
}