# Management_system

Program konsolowy napisany w języku Java, służący do analizowania danych z plików Excel oraz generowania raportów dotyczących pracowników, projektów i czasu pracy.

Program wczytuje dane z katalogu zawierającego pliki w formacie .xlsx, a następnie generuje wybrane raporty na podstawie argumentów podanych w konsoli.

## Sposób użycia

Program można uruchomić z poziomu konsoli za pomocą komendy:

java -jar Analyzer.jar C:\Users\UserName\Desktop\Raporty -r1 -r2

W powyższym przykładzie program:

uruchamia plik Management_system.jar,
wczytuje dane z katalogu C:\Users\UserName\Desktop\Raporty,
generuje raporty r1 oraz r2.

## Argumenty

Program obsługuje następujące argumenty:

Argument	Opis
-r1	Generuje raport czasu pracy pracowników przy projektach.
-r2	Generuje raport podsumowania godzin dla projektów.

## Raporty

Raport 1 - Czas pracy pracowników przy projektach projektach
Raport przedstawia liczbę godzin pzepracowanych przez pracowników przy konkretnych projektach:


| Imię Nazwisko | godziny | projekty |
| -------------- | ------- | -------- |
| Jan Kowalski   | 20      | xcd      |
| Anna Gałka    | 130     | abc      |

Raport 2 - Podsumowanie godzi dla projektów
Raport przedstawia łączną liczbę godzin przypisaną do każdego projektu w określonym zakresie dat.


| Nazwa Projektu | ile godzin | zakres dat              |
| -------------- | ---------- | ----------------------- |
| xcd            | 130        | 01.01.2020 - 01.01.2021 |
| abc            | 200        | 01.12.2021 - 01.01.2025 |

```
