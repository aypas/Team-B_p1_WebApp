node("master") {
    stage("Build") {
        checkout scm
        sh "ls"
        unarchive mapping: ['ORM-testing-1.0.jar': 'ORM-testing-1.0.jar']
        sh "ls"
    }
}