# Management_system

Program konsolowy napisany w języku Java, służący do analizowania danych z plików Excel oraz generowania raportów dotyczących pracowników, projektów i czasu pracy.

Program wczytuje dane z katalogu zawierającego pliki w formacie .xlsx, a następnie generuje wybrane raporty na podstawie argumentów podanych w konsoli.

## Sposób użycia

Program można uruchomić z poziomu konsoli za pomocą komendy:

java -jar Management_system.jar C:\Users\UserName\Desktop\Raporty -r1 -r2

W powyższym przykładzie program:

uruchamia plik Management_system.jar,
wczytuje dane z katalogu C:\Users\UserName\Desktop\Raporty,
generuje raporty r1 oraz r2.

## Argumenty

Program obsługuje następujące argumenty:

| Argument | Opis |
|----------| --- |
| r1       | Generuje raport czasu pracy pracowników przy projektach |
| r2       | Generuje raport podsumowania godzin dla projektów    |

## Raporty

Raport 1 - Czas pracy pracowników przy projektach.
Raport przedstawia liczbę godzin pzepracowanych przez pracowników przy konkretnych projektach:

| Imię Nazwisko | ile godzin | procent czasu | * Nazwa projektu |
|---------------|------------|---------------|------------------|
| Jan Kowalski  | 20         | 20%           |                  |
| Anna Gałka    | 50         | 40%           |                  |

Raport 2 - Podsumowanie godzin dla projektów.
Raport przedstawia łączną liczbę godzin przypisaną do każdego projektu w określonym zakresie dat:


| Nazwa Projektu | suma godzin | zakres dat              |
|----------------|-------------| ----------------------- |
| Projekt 1      | 130         | 01.01.2020 - 01.01.2021 |
| Projekt 2      | 200         | 01.12.2021 - 01.01.2025 |


Raport 4 - Podsumowanie godzin dla zadań (sortowanie od największej liczby godzin).
Raport przedstawia łączną liczbę godzin spędzoną nad zadaniami danego typu:


| Nazwa Zadania | ile godzin   | *tagi |
|---------------|--------------|-------|
| Zadanie 1     | 130          |       |
| Zadanie 2     | 200          |       |

```

```
