job('deploy_site_prod') {
    scm {
        git {
            remote {
                github('aaron-c-zhao/chengrui.nl', 'ssh')
                credentials('jenkins-github-sync')
            }
            branch('deploy')
            extensions {
                cleanBeforeCheckout()
                relativeTargetDirectory('chengrui.nl')
            }
        }
    }
    parameters {
        stringParam('path_to_site', '/home/jenkins/workspace/grui.nl_initial-jenkins-pipeline/_site/', 'path to site artifact')
        stringParam('git_usr', null, 'github user')
        stringParam('git_cred', null, 'github private key path')
    }
    steps {
        shell(readFileFromWorkspace('scripts/upload_site.sh'))
    }
}