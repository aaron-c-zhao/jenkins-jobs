job('auto_vault_backup') {
    triggers {
        cron('@daily')
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