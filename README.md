# shit-code-cloud-java

## git2consul

npm config set registry http://registry.cnpmjs.org


## docker集群
#### win10
需要：(docker toolbox 19.03.1)[https://github.com/docker/toolbox/releases]

如果提示异常，下载boot2docker.iso，放在C:\Users\{yourname}\.docker\machine\cache路径

添加路由到win，ssh到虚拟机
```cmd
# add route 172.17.0.0 mask 255.255.0.0 192.168.99.100 -p
# docker-machine ssh
```

打开虚拟机的路由转发

```shell script
$ sudo -i
# iptables -P FORWARD ACCEPT
# sysctl net.ipv4.ip_forward
net.ipv4.ip_forward = 1
```
如果不是1，需要在/var/lib/boot2docker/profile文件添加net.ipv4.ip_forward=1，重启

docker run redis:last

应该可以ping通容器ip

添加项目路径docker/cluster到虚拟机共享目录/docker/

boot2docker 只有/var/lib/boot2docker/bootlocal.sh这个脚本能持久化
