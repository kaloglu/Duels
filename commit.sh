#!/bin/bash
set -e
    git config --global user.email "build@travis-ci.com"
    git config --global user.name "Travis CI"
    echo 'add remote'
    git remote add release "https://$GH_TOKEN@github.com/kaloglu/Duels.git"
    echo 'checkout $TRAVIS_BRANCH'
    git checkout $TRAVIS_BRANCH

latest=$(git tag  -l --merged master --sort='-*authordate' | head -n1)
echo 'ver =》 ${latest}'
semver_parts=(${latest//./ })
major=${semver_parts[0]}
minor=${semver_parts[1]}
patch=${semver_parts[2]}
 
version=${major}.${minor}.${patch}
 
echo 'final ver =》 ${version}'

if [ "$TRAVIS_BRANCH" = "master" -a "$TRAVIS_PULL_REQUEST" = "false" ]; then
    ./gradlew postBeta
    echo 'log $TRAVIS_COMMIT'
    git log --format=%B -n 1 $TRAVIS_COMMIT > beta_release_notes.txt
    echo 'add -u'
    git add . -u
    echo 'tag version ${version}'
    git commit -m "[ci skip] Setting version to ${version}"
    echo 'push release $TRAVIS_BRANCH'
    git push -f release $TRAVIS_BRANCH 2>&1
    ./gradlew crashlyticsUploadDistributionFirebaseBeta --stacktrace --no-daemon
fi
    echo 'tagged version ${version}'
    git tag v${version} -a -m "Tagging version v${version}"
    git push -f release $TRAVIS_BRANCH --tags 2>&1
