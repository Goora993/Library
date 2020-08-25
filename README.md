# LibraryApp
Projekt prostej konsolowej aplikacji bibliotecznej oparty na kursie Java SE od Javastart.pl. <br>
Celem tworzenia tego projektu jest utrwalenie podstaw języka Java oraz nauka funkcji systemu kontroli wersji GIT.

Aplikacja ma za zadanie umożliwiać użytkownikowi dodawanie nowych pozycji (czasopisma i książki) do biblioteki, bezproblemowy odczyt ich po każdym, ponownym uruchomieniu aplikacji,
co zapewnia zapisywanie treści w przeznaczonych do tego plikach. 
W przyszłości aplikacja otrzyma interfejs graficzny stworzony w technologii JavaFX. 

12/08/2020:
- utworzenie projektu, dodanie pierwszej klasy modelu oraz klasy z metodą main

13/08/2020:
- dodanie dodatkowej klasy odpowiedzialnej za wczytywanie danych od użytkownika
- dodanie funkcji tworzenia książek na podstawie informacji przekazywanych przez użytkownika

17/08/2020:
- dodanie klasy kontrolera zawierającej w sobie główną pętlę programu
- dodanie klasy LibraryApp zawierajacej w sobie metodą main
- modyfikacja klasy Library w taki sposób, aby była teraz elementem modelu aplikacji, a obiekt tej klasy 
mógł zawierać informacje na temat książek w postaci tablicy o powiększającym się rozmiarze wraz ze wzrostem ilości 
ksiażek dodawanych do biblioteki

18/08/2020:
- wykorzystanie polimorfizmu i utworzenie jednej tabeli przechowującej obiekty Book i Magazine
- utworzenie możliwości dodawania osobno magazynów i książek
- utworzenie możliwości wyświetlania informacji osobno na temat książek, magazynów lub wszystkich obiektów 
w bibliotece na raz

19/08/2020:
- wykorzystanie typu wyliczeniowego Enum w klasie Option, by usprawnić funkcjonowanie menu aplikacji
- wykorzystanie obiektu StringBuffer i metody append by usprawnić wyświetlanie informacji na temat publikacji 
w bibliotece

20/08/2020:
- dodanie obsługi wyjątków w momentach, gdzie użytkownik może wprowadzić błędne dla funkcjonowania programu
dane
- dodanie klasy wyjątku NoSuchOptionException w celu sprecyzowania czego wyjatek dotyczy, a także przećwiczenia tworzenia
własnych klas wyjątków.
- zaimplementowanie zmian w klasach wymaganych do poprawnego działania programu po dodaniu funkcji obsługi wyjątków.

21/08/2020:
- przekształcenie klasy Publication na klasę abstrakcyjną w celu zabezpieczenia się przed przypadkowym
tworzeniem obiektów tego typu
- dodanie funkcji zapisywania publikacji przy każdym zamknięciu aplikacji w pliku Library.o oraz 
wczytywanie listy publikacji z tego samego pliku podczas każdego uruchomienia programu. W tym celu 
utworzono interfejs FileManager i jego implementację SerializableFileManager, które pozwalają
zapisywać obiekt klasy Library przy pomocy serializacji (klasa Library i Publication implementują
interfejs Serializable) do pliku Library.o 

22/08/2020:
- dodanie funkcji zapisaywania publikacji w pliku o rozszerzeniu .csv na podobnych zasadach jak przy wykorzystaniu
serializacji, jednak przy wykorzystaniu innych mechanizmów zawartych głównie w klasie CsvFileManager

24/08/2020:
- utworzenie generatora unikalnych ID dla publikacji, po to aby w przyszłości utworzyć możliwość 
usuwania publikacji przy jego pomocy

25/08/2020:
- wydzielenie z głównej klasy LibraryControl, w celu poprawienia czytelności kodu, pomniejszych klas odpowiedzialnych 
za określone funkcjonalności aplikacji (InfoControl, AddControl, RemoveControl)