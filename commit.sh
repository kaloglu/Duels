#!/usr/bin/env bash
  git config --global user.email "travis@kaloglu.com"
  git config --global user.name "travis"
  git add -u
  git commit -m "[ci skip] $TRAVIS_BUILD_NUMBER \n" + $(git log --format=%B -n 1 $TRAVIS_COMMIT)
  git push "https://kaloglu:$GITHUB_API_KEY@github.com/kaloglu/Duels.git" master > /dev/null 2>&1
  echo  -e " Done \ n "