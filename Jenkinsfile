node("master") {
    stage("Build") {
        checkout scm
        sh "ls"
        unarchive mapping: ['target/': './ORM-testing-1.0.jar']
        copyArtifacts mapping: [projectName: 'sourceproject']
        sh "ls"
    }
}