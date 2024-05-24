# PostgreSQL resmi imajını kullanarak başlayın
FROM postgres:latest


# backup.sql dosyasını konteynere kopyalayın
COPY backup.sql /docker-entrypoint-initdb.d/

# PostgreSQL kurulumunu ve pg_isready ile hazır olup olmadığını kontrol eden scripti kurun
RUN apt-get update && apt-get install -y postgresql-client



# PostgreSQL'in hazır olduğunu kontrol edin ve ardından SQL scriptlerini çalıştırın
CMD ["sh", "-c", "while ! pg_isready -U postgres -h bank-app-db-1 -p 5432; do sleep 1; done && psql -U postgres -d postgres -h bank-app-db-1 -f /docker-entrypoint-initdb.d/backup.sql"]
