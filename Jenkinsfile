node("master") {
    stage("Build") {
        checkout scm
        sh "ls"
        unstash 'ORM-testing-1.0.jar'
        sh "ls"
    }
}