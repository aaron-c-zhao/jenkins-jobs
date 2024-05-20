FROM node:slim

WORKDIR /vault_export
RUN apt update && apt-get install -y build-essential python3
RUN npm install -g @bitwarden/cli

COPY export.sh ./export.sh 
RUN chmod +x export.sh
ENTRYPOINT [ "bash", "-c", "/vault_export/export.sh" ]