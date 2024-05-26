job('auto_vault_backup') {
    triggers {
        cron('@daily')
    }
    scm {
        git {
            remote {
                github('aaron-c-zhao/secret', 'ssh')
                credentials('jenkins-github-sync')
            }
            extensions {
                cleanBeforeCheckout()
                wipeOutWorkspace()
            }
        }
    }

    parameters {
        stringParam('git_usr', null, 'github user')
        stringParam('git_cred', null, 'github private key path')
    }

    steps {
        wrappers {
            buildInDocker {
                dockerfile ()
                volume("${WORKSPACE}/export", "/vault_export")
            }
        }
    }
}