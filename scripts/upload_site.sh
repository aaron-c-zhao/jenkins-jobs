#!/bin/bash

set -e

echo ">>>> Changing directory to repository <<<<"
cd chengrui.nl

echo ">>>> Checkouting deploy branch <<<<"
git checkout deploy

echo ">>>> Removing everything in directory <<<"
rm -rf *

echo ">>>> Copying and uploading site <<<<"

all_site_files="${path_to_site}*"
cp -r $all_site_files .

# determine if there's anything to commit
if [ -n "$(git diff)" ]; then
    echo ">>>> commiting change <<<<"
    git config --global user.email "aaron.zhaocr+jenkins@gmail.com"
    git config --global user.name "jenkins"

    git add . 
    git commit -m "auto deployment ${date}"
fi

# check if HEAD is ancestor of HEAD of origin/deploy
if [ $(git merge-base --is-ancestor HEAD @{u}) ]; then
    echo ">>>> Nothing to push <<<<"
    exit 0
fi

GIT_SSH_COMMAND="ssh -i ${git_cred}" git push
echo ">>>> site deployed <<<<"