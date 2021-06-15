#Projekt Internet of Things Devices

#Przykładowy sposób na uruchomienie backendu projektu
- Zaimportować projekt w IDE
- Uruchomić w dockerze plik docker-compose.yml i upewnić się, że wszystkie cztery serwisy wystartowały poprawnie.
  (W razie potrzeby uruchomić pojedynczy serwis ponownie)
- Uruchomić projekt za pomocą taska bootRun z gradle lub za pomocą IDE (klasa main to IoTDeviceApplication)

Aby uruchomić testy backendu, można uruchomić taska test z gradle lub zaimportować projekt w IDE 
np Intellij i uruchomić testy znajdujące się w src/test

Dodatkowo, aby autoryzować się należy wykorzystać następujące dane:
ADMIN login: "admin" hasło: "admin"
MANAGER login: "manager" hasło: "manager"
USER login: "user" hasło: "user"



#Przykładowy sposób na uruchomienie frontendu projektu
- Wejść w folder react
- Uruchomić komendę npm install  
- Uruchomić komendę npm start

Aby uruchomić testy frontendu, można zaimportować projekt w IDE np WebStorm i uruchomić 
testy znajdujące się w app/containers/App/tests/project.test.js
