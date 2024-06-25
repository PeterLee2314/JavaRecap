#### Git
git status // see what modified and untracked files(new file)
git log // see commit log (q to exit)
git log --pretty=oneline (commit message only)
git log --graph  (more detailed on extension : git graph)

git diff // see what is modified in file
git diff --staged // see files modified in stage after 'git add'
git history
git config

git add // its stage
git commit -am // a (skip staging, no need to use 'git add')

git push -u origin main (-u mean upstream and branch name is main)  // master != main, ppl now use main instead of master
git push origin (origin mean URL)
git remote -v (see the origin URL)

git branch (see how many branch)
git branch --all (see all branch include github)
git checkout -b == git switch -b == git switch -c
git switch - (go previous branch)
git branch -d branchName (remove branch)

#### Way to merge code
git merge
git merge branchName (so it merge the current branch you at and the branch you selected)
git rebase
git pull origin main

#### Git tag
git tag (publish to user, so give a tag, check how many tag so far) [two options: lightweight / annotated]
annotated => more information (-a) 
eg git tag -a v1.0 -m '1st release'
git show v1.0 (give description for particular tag)
git push origin v1.0 (push tag individually to files)

#### git remove from git repo
if delete file from working directory and commit (ITS WRONG)
use 'git rm --cache files.txt' first then directly delete it and then commit 

#### Create new repo on local machine with github
git init -> git add README.md -> git commit -m "init" -> git branch -M main -> git remote add origin HTTPS/SSH -> git push -u origin main

HTTPS (login and authenticate yourself) VS SSH (no need login )
ssh-keygen -o (first time to do it on a machine, so no need to authenticate yourself on computer)
.ssh/id_rsa.pub , copy the RSA and go GITHUB SETTING and 'SSH and GPG keys' paste that key , then now the SSH remote add
is good to go

#### clone project
git clone https
#### fork project 

#### Push my code to the empty github repo

