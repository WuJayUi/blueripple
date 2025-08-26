完整流程指令

1.生成 SSH key

ssh-keygen -t ed25519 -C "郵箱@example.com"


提示 Enter file in which to save the key (/home/用户名/.ssh/id_ed25519): → 直接按 Enter

提示 Enter passphrase (empty for no passphrase): → 可以留空，直接 Enter

提示 passphrase → Enter

生成文件：

~/.ssh/id_ed25519       ← 私鑰
~/.ssh/id_ed25519.pub   ← 公鑰

2.查看並複製公鑰

cat ~/.ssh/id_ed25519.pub


複製整行到GitHub

3.添加SSH key到GitHub

登錄GitHub → Settings → SSH and GPG keys → New SSH key

貼上剛的公鑰

Add SSH key

4.啟動SSH agent 並添加私鑰

eval "$(ssh-agent -s)"
ssh-add ~/.ssh/id_ed25519

5.配置本地倉庫使用SSH URL

cd /path/to/your/project
git remote set-url origin git@github.com:用戶名/倉庫名.git

範例:
git remote set-url origin git@github.com:WuJayUi/blueripple.git

6.測試

ssh -T git@github.com

成功顯示
Hi WuJayUi! You've successfully authenticated, but GitHub does not provide shell access.

7. Push  GitHub

git push -u origin main


-u 表示把本地 main 分支設置為跟蹤遠程 main

之後git push 或 git pull 不用輸入密碼
