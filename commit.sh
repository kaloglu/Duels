#!/bin/bash
set -e
if [ "$TRAVIS_BRANCH" = "master" -a "$TRAVIS_PULL_REQUEST" = "false" ]; then
    ./gradlew postBeta --stacktrace --no-daemon
    git config --global user.email "build@travis-ci.com"
    git config --global user.name "Travis CI"
    echo 'add remote'
    git remote add release "https://$GH_TOKEN@github.com/kaloglu/Duels.git"
    echo 'checkout $TRAVIS_BRANCH'
    git checkout $TRAVIS_BRANCH
    echo 'log $TRAVIS_COMMIT'
    git log --format=%B -n 1 $TRAVIS_COMMIT > beta_release_notes.txt
    echo 'add -u'
    git add . -u
    echo 'tag version $GIT_TAG_VERSION'
    git commit -m "[ci skip] Setting version to $GIT_TAG_VERSION"
    echo 'tagged version $GIT_TAG_VERSION'
    git tag v$GIT_TAG_VERSION -a -m "Tagging version v$GIT_TAG_VERSION"
    echo 'push release $TRAVIS_BRANCH'
    git push -f release $TRAVIS_BRANCH 2>&1
    git push -f release $TRAVIS_BRANCH --tags 2>&1
    ./gradlew crashlyticsUploadDistributionFirebaseBeta --stacktrace --no-daemon
fi