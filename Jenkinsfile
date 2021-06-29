node("master") {
    stage("Build") {
        checkout scm
        sh "ls"
        unarchive mapping: ['target/': 'ORM-testing-1.0.jar']
        sh "ls"
    }
}