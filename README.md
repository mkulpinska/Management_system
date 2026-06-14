# Management_system

Program konsolowy napisany w języku Java, służący do analizowania danych z plików Excel oraz generowania raportów dotyczących pracowników, projektów i czasu pracy.

Program wczytuje dane z katalogu zawierającego pliki w formacie .xlsx, a następnie generuje wybrane raporty w formacie csv. oraz PDF. na podstawie argumentów podanych w konsoli.

## Sposób użycia

Program można uruchomić z poziomu konsoli za pomocą komendy:

java -jar Management_system.jar -g -rEmp -rTyp=CSV -p=Reports/2025/07/

W powyższym przykładzie program:

Wczytuje dane z plików Excel,
uzywa katalogu Reports/2025/07/
generuje raport pracowników w fomacie CSV

## Argumenty

Program obsługuje następujące argumenty:

| Argument | Opis                                             |
|----------|--------------------------------------------------|
| -g       | Wczytuje dane z plików Excel                     |
| -rEmp    | Generuje raport podsumowania godzin dla projektów |
| -rProj   | Generuje projektów                               |
| -rTask   | Generuje raport zadań                            |
| -rTyp=PDF    | Generuje raport w formacie PDF                   |
| -rTyp=CSV  | Generuje projektów                               |
| -rTyp=Con   | Wyświetla raport w konsoli                       |
| -p=ścieżka  | Ustawia ścieżkę do folderu z plikami wejściowymi |

## Przykłady uruchomienia
Wczytanie danych z Excela i wygenerowanie raportu pracowników do CSV

`java -jar Management_system.jar -g -rEmp -rTyp=CSV -p=Reports/2025/07/`

Wygenerowanie raportu pracowników do PDF

`java -jar Management_system.jar -rEmp -rTyp=PDF`

Wyświetlenie raportu pracowników w konsoli

`java -jar Management_system.jar -rEmp -rTyp=Con`

Wygenerowanie raportu projektów

`java -jar Management_system.jar -rProj -rTyp=CSV`


## Raporty

Raport 1 - `-rEmp` Czas pracy pracowników przy projektach.
Raport przedstawia liczbę godzin pzepracowanych przez pracowników przy konkretnych projektach:


| Imię Nazwisko | ile godzin | 
| -------------- | ---------- |
| Jan Kowalski   | 20         |
| Anna Gałka    | 50         |

Raport 2 - `-rProj` Podsumowanie godzin dla prjektów oraz zakres dat.
Raport przedstawia łączną liczbę godzin przypisaną do każdego zadania


| Nazwa Projektu | suma godzin | zakres dat              |
| -------------- | ----------- | ----------------------- |
| Projekt 1      | 130         | 01.01.2020 - 01.01.2021 |
| Projekt 2      | 200         | 01.12.2021 - 01.01.2025 |

Raport 3 - `-rTask` Podsumowanie godzin dla zadań.
Raport przedstawia łączną liczbę godzin spędzoną nad zadaniami danego typu:


| Nazwa Zadania | ile godzin |
| ------------- | ---------- |
| Zadanie 1     | 130        |
| Zadanie 2     | 200        |


## Typ raportu

| Format | Opis |
|--------|------|
| PDF    | Zapisuje raport jako plik PDF  |
| CSV    | Zapisuje raport jako plik CSV  |
| Con    | Wyświetla raport w konsoli  |
| XLM    | Aktualnie działa jak wydruk konsolowy |

```

```
