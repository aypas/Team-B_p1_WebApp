node("master") {
    stage("Build") {
        checkout scm
        sh "ls"
        unarchive mapping: ['target/': '.']
        sh "ls"
    }
}