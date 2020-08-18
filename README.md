# LibraryApp
Projekt prostej konsolowej aplikacji bibliotecznej oparty na kursie Java SE od Javastart.pl. <br>
Celem tworzenia tego projektu jest utrwalenie podstaw języka Java oraz nauka funkcji systemu kontroli wersji GIT.

Aplikacja ma za zadanie umożliwiać użytkownikowi dodawanie nowych pozycji (czasopisma i książki) do biblioteki, bezproblemowy odczyt ich po każdym, ponownym uruchomieniu aplikacji,
co zapewnia zapisywanie treści w przeznaczonych do tego plikach. 
W przyszłości aplikacja otrzyma interfejs graficzny stworzony w technologii JavaFX. 

12/08/2020:
- utworzenie projektu, dodanie pierwszej klasy modelu oraz klasy z metodą main.

13/08/2020:
- dodanie dodatkowej klasy odpowiedzialnej za wczytywanie danych od użytkownika, 
- dodanie funkcji tworzenia książek na podstawie informacji przekazywanych przez użytkownika

17/08/2020:
- dodanie klasy kontrolera zawierającej w sobie główną pętlę programu,
- dodanie klasy LibraryApp zawierajacej w sobie metodą main,
- modyfikacja klasy Library w taki sposób, aby była teraz elementem modelu aplikacji, a obiekt tej klasy 
mógł zawierać informacje na temat książek w postaci tablicy o powiększającym się rozmiarze wraz ze wzrostem ilości 
ksiażek dodawanych do biblioteki.

18/08/2020:
- wykorzystanie polimorfizmu i utworzenie jednej tabeli przechowującej obiekty Book i Magazine,
- utworzenie możliwości dodawania osobno magazynów i książek,
- utworzenie możliwości wyświetlania informacji osobno na temat książek, magazynów lub wszystkich obiektów w bibliotece na raz.

