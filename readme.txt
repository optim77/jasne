Folder backend zawiera skompilowany serwer napisany Spring Boot
Folder db zawiera skrypt inicjalizujący oraz wolium danych kontenera postgres
Folder front zawiera pliki klienta webowego
Folder nginx zawiera konfigurację reverse proxy
Zmienne środowiskowe umieszczone są w pliku .env
W backend/Dockerfile w komendzie uruchomieniowej są kolejne zmienne, z którymi jest uruchamiany serwer
Aplikację można uruchomić za pomocą docker compose uruchamiając komendę docker compose up w głównym katalogu

Kod serwera api znajduje się na branchu main: https://github.com/optim77/jasne
Kod klienta webowego wraz ze skryptami wdrażania na serwer znajduje się na branchu deploy: https://github.com/optim77/jasne/tree/deploy

Uruchomienie:

1. Zbudowana wersja aplikacji została udostępniona pod adresem: http://20.123.57.10/

2. Uruchomienie lokalnie za pomocą dockera: w głównym folderze aplikacji gdzie znajduje się plik docker-compose.yaml należy uruchomić polecenie docker-compose up -d

3. W celu lokalnego uruchomienia poszczególnych serwisów aplikacji należy:
    Uruchomić serwer backnedowy za pomocą polecenia java -jar dstay-0.0.7-SNAPSHOT.jar
    Serwer wymaga połączenia do bazy danych postgres pod adresem postgresql://db:5432/jasne username: postgres hasło: root
    Serwer zostanie uruchomiony na standardowym porcie: 8080

    Uruchomienie deweloperskiego serwera klienckiego należy wykonać w folderze /front za pomocą polecenia npm start
    W pliku /front/.env znajduje się zmienna środowiskowa, która odpowiada za adresację backnedu, zakomentowana wartość to adres zdalnego serwera API z punktu 1

Poświadczenia dla panelu administratora, który znajduje się pod adresem /admin:
admin@jasne.com
123123

Przykładowe dane do serwera spring wgrywane są bezpośrednio w aplikacji, znajdują się one w ścieżce dstay\src\main\resources\data.sql