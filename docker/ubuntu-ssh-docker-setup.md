# Ubuntu SSH Docker Setup – End‑to‑End Summary

This document summarizes the **complete, correct setup** for running an Ubuntu Docker container with SSH access using **key‑based authentication**, including Dockerfile and docker‑compose.

---

## 1. Prerequisites (Host – macOS / Linux)

- Docker installed
- SSH key on host:
```bash
ls ~/.ssh/id_ed25519.pub
```

If not present:
```bash
ssh-keygen -t ed25519
```

---

## 2. Core Concept (Important)

- Docker containers are **not VMs**
- On macOS / Windows:
  - You connect via **localhost + mapped port**
  - Container IP (172.x.x.x) is **not reachable**
- SSH daemon must be started **directly**, not via `service`

---

## 3. Dockerfile (Best Practice)

```dockerfile
FROM ubuntu:24.04

RUN apt update && \
    apt install -y openssh-server && \
    mkdir /var/run/sshd

# Allow root login via SSH key
RUN sed -i 's/#PermitRootLogin prohibit-password/PermitRootLogin yes/' /etc/ssh/sshd_config && \
    sed -i 's/#PubkeyAuthentication yes/PubkeyAuthentication yes/' /etc/ssh/sshd_config && \
    sed -i 's/#PasswordAuthentication yes/PasswordAuthentication no/' /etc/ssh/sshd_config

# Create .ssh directory
RUN mkdir -p /root/.ssh && chmod 700 /root/.ssh

EXPOSE 22

CMD ["/usr/sbin/sshd","-D"]
```

---

## 4. Build the Image

```bash
docker build -t ubuntu-ssh .
```

---

## 5. docker-compose.yml

```yaml
version: "3.8"

services:
  ubuntu-ssh:
    image: ubuntu-ssh
    container_name: ubuntu-ssh
    ports:
      - "2222:22"
    volumes:
      - ~/.ssh/id_ed25519.pub:/root/.ssh/authorized_keys:ro
    restart: unless-stopped
```

---

## 6. Start the Container

```bash
docker compose up -d
```

Verify:
```bash
docker ps
```

---

## 7. SSH into the Container (Host)

```bash
ssh -p 2222 root@localhost
```

No password required.

---

## 8. Why SSH Fingerprint Appears Sometimes

SSH asks to trust a host **when**:
- First connection
- Container rebuilt (new host key)
- `known_hosts` entry removed

Stored at:
```bash
~/.ssh/known_hosts
```

Strict check setting:
```bash
ssh -G localhost | grep StrictHostKeyChecking
```

---

## 9. Common Errors & Fixes

| Issue | Cause | Fix |
|-----|-----|-----|
| Permission denied (password) | Root locked | Use SSH key |
| Permission denied (publickey) | Key missing | Add to authorized_keys |
| sshd fails to start | service/systemd | Use `/usr/sbin/sshd -D` |
| container exits | no foreground process | sshd must be CMD |
| cannot reach 172.x.x.x | Docker Desktop | Use localhost |

---

## 10. Golden Rules (Remember)

- Use **SSH keys**, never passwords
- Never use `service ssh start` in Docker
- macOS → always `localhost:PORT`
- Container dies = main process exited

---

## 11. Final Test Checklist

```bash
docker ps
ssh -p 2222 root@localhost
```

✅ If this works, everything is correct.

---

**You now have a production‑correct Ubuntu SSH Docker setup.**
