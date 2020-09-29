# LibraryApp
Projekt prostej konsolowej aplikacji bibliotecznej. Pomysł aplikacji i "szkielet" pochodzi z kursu JavySE przygotowanego
przez ekipę Javastart.pl, jednak aplikacja została przeze mnie wzbogacona o kilka moich pomysłów. 
Celem tworzenia tego projektu było utrwalenie podstaw języka Java oraz poznawanie funkcji systemu kontroli wersji GIT.




Podstawowe funkcjonalności aplikacji:
- dodawanie i usuwanie publikacji mających unikalne, generowane, numeryczne id
- sortowanie i wyświetlanie publikacji dostępnych w bibliotece
- dodawanie i usuwanie użytkowników
- zarządzanie użytkownikami (np. wypożyczanie ksiażek)
- domyślne zapisywanie danych publikacji i użytkowników przy pomocy serializacji do plików o rozszerzeniu *.o
- domyślne wczytywanie danych z pliku o rozszerzeniu *.o każdorazowo przy uruchomieniu programu
- zapisywanie danych do plików *.csv, które następnie można otworzyć w arkuszu kalkulacyjnym
- wczytywanie danych z plików *.csv
- wybór ścieżki zapisu lub odczytu danych w formie plików *.csv
- możliwość zapisania postępów prac bądź wczytanie ostatniego stanu biblioteki w trakcie korzystania z aplikacji
        bez konieczności ponownego uruchamiania jej
        
                
Jak we wstępie zostało wspomniane jest to póki co tylko aplikacja konsolowa, jednak w przyszłości w ramach przećwiczenia 
i utrwalenia umiejętności korzystania z JavaFX i Mavena, chciałbym wzbogacić aplikację o proste GUI.





Co i kiedy było robione:

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

26/08/2020:
- dodanie funkcji usuwania publikacji przy pomocy ID lub pełnej informacji nt. publikacji
- rozdzielenie klasy enum Option na pomniejsze analogicznie do rozdzielenia klasy LibraryControl i umieszczenie określonej
klasy i odpowiadającego jej enuma w oddzielnym pakiecie w celu poprawienia czytelności kodu oraz struktury plików i folderów
- utworzenie klas komparatorów w celu dodania funkcji sortowania publikacji podczas ich wyświetlania
- dodanie funkcji sortowania książek według tytułu lub autora
- dodanie funkcji sortowania magazynów według nazwy lub wydawnictwa 

28/08/2020:
- przerobienie sposobu przechowywania publikacji z tablicy na mapę, dzięki czemu zaoszczędzono na ilości linijek kodu i 
jego czytelności,
- przygotowanie aplikacji pod dodanie funkcjonalności dodawania użytkowników poprzez utworzenie pustych klas 
analogicznych do klas odpowiedzialnych za funkcjonalności związane z publikacjami

29/08/2020:
- wypełnienie pustych klas metodami i utworzenie opcji dodawania, usuwania i wyświetlania użytkowników biblioteki

31/08/2020:
- rozdzielenie klasy LibraryControl na mniejsze klasy obsługujące funkcje związane z publikacjami i użytkownikami
- dodanie importu i eksportu użytkowników do pliku Users.csv

01/09/2020:
- dodanie importu i eksportu publikacji i użytkowników przy pomocy serializacji do plików z rozszerzeniem .o 

02/09/2020:
- uporządkowanie konstruktora klasy LibraryControl
- naprawienie problemów pojawiających się przy imporcie serializowanych obiektów

04/09/2020:
- dodanie możliwości wypożyczania i oddawania książek przez użytkowników
- dodawanie możliwości wyświetlania szczegółów użytkownika na podstawie jego nr pesel
- dodanie szablonu plików potrzebnych do zaimplementowania możliwości zapisywania danych do pliku w trakcie
korzystania z aplikacji

05/09/2020:
- ustawienie zapisu przy pomocy serializacji jako domyślnego sposobu zapisu plików
- utworzenie mechanizmu umożliwiającego zapisywanie danych do pliku w trakcie korzystania z aplikacji bez
konieczności jej wyłączania by utworzyć zapis (tak jak było to wcześniej)
- dodanie możliwości zapisywania i wczytywania danych na podstawie wybranej przez użytkownika ściezki do pliku 

14/09/2020:
- modyfikacja klas odpowiedzialnych za kontrolę publikacj:
    * zmniejszenie ilości bloków try/catch
    * uporzadkowanie obsługi wyjątków w taki sposób, aby użytkownik w razie wyboru błędnej wartości pozostawał 
    w menu, w którym aktualnie się znajduje 
    * rezygnacja z częsci klas wyjątków utworzonych wcześniej w celu poprawności czytelności kodu
  
17/09/2020:
- modyfikacja klas odpowiedzialnych za kontrolę użytkowników:
    * zmniejszenie ilości bloków try/catch
    * uporzadkowanie obsługi wyjątków w taki sposób, aby użytkownik w razie wyboru błędnej wartości pozostawał 
    w menu, w którym aktualnie się znajduje 
    * rezygnacja z częsci klas wyjątków utworzonych wcześniej w celu poprawności czytelności kodu
    * dodanie komunikatów powiadamiających użytkownika o wypożyczeniu bądź zwróceniu publikacji 
    
19/09/2020:
- modyfikacja klas odpowiedzialnych za kontrole zapisu i wczytywania danych:
    * zmniejszenie ilości bloków try/catch
    * uporzadkowanie obsługi wyjątków w taki sposób, aby użytkownik w razie wyboru błędnej wartości pozostawał 
    w menu, w którym aktualnie się znajduje 
    * rezygnacja z częsci klas wyjątków utworzonych wcześniej w celu poprawności czytelności kodu
- uporzadkowanie części kodu, tak aby był bardziej przejrzysty
- usunięcie niektórych, niepotrzebnych już klas wyjątków

20/09/2020:
- uporządkowanie większości klas w taki sposób aby kod był bardziej przejrzysty i czytelny (usuwanie nieużywanych
importów, usuwanie zbędnych przerw między metodami itp.)
- utworzenie abstrakcyjnych klas odpowiedzialnych za wyświetlanie informacji o publikacjach oraz użytkownikach i 
scalenie ich funkcjonalności w klasie ConsolePrinter
- poprawienie wyświetlania się komunikatów w klasach odpowiedzialnych za kontrolę zapisu i wczytywania danych

21/09/2020:
- naprawienie błędów w menu wyświetlania informacji nt. publikacji:
    * przeniesienie funkcjonalności odpowiedzialnych za wyświatlanie informacji o ksiażkach do klasy InfoBookControl
    * przeniesienie funkcjonalności odpowiedzialnych za wyświatlanie informacji o czasopismach do klasy InfoMagazineControl
    
23/09/2020:
- utworzenie funkcji okreslającej czy dana publikacja jest aktualnie wypożyczona czy nie, w tym celu zmodyfikowano 
klasy modelu danych (Publication, Book i Magazine) oraz klasy odpowiedzialne za funkcjonalność wypożyczania i zwracania 
publikacji
- dodano dwie nowe klasy wyjątków mające na celu informowanie użytkownika o problemie z wypożyczeniem danej publikacji 
(NoSuchPublicationBorrowedException i PublicationIsAlreadyBorrowedException)

25/09/2020:
- naprawienie systemu wczytywania i zapisywania danych w trakcie działania programu:
    * obiekt klasy Library w klasie LibraryControl od teraz jest statyczny i prywatny
    * wykorzystanie setera do modyfikacji statycznego obiektu library podczas ładowania danych
    * wykorzystywanie getera do zmiany referencji obiektów library w poszczególnych klasach, w których library odwołuje się
    do nieaktualnego już obiektu
- dodanie możliwości wyboru przez użytkownika czy podczas zamykania aplikacji chce nadpisywać dane 

26/09/2020:
- zmodyfikowanie klas odpowiedzialnych za wypożyczanie i oddawanie publikacji w celu wyeliminowania zbyt dużej ilości rzutowania
- zmieniono sposób odczytu danych z pliku CSV ze standardowego odczytywania za pomocą klasy Scanner i pętli, na odczytywanie
za pomocą strumieni
- zmodyfikowanie działania FileManagera, tak aby przy pomocy klasy FileManagerBuilder był tworzony odpowiedni obiekt w stosunku
do potrzeb użytkownika, poza tym w FileManagerBuilder wykorzystano mechanizm zmuszający aplikację do korzystania z wcześniej 
utworzonych obiektów klas CsvFileManager i SerializableFileManager po to aby zabezpieczyć się przed tworzeniem zbędnych obiektów

28/09/2020:
- przeniesienie funkcjonalności wybierania przez użytkownika ścieżki zapisu/odczytu danych z klasy DataReader do interface
FileManager, tak aby było to usytuowane w kodzie w bardziej odpowiednim miejscu

29/09/2020:
- zmieniono sposób zapisywania dat w klasach dziedziczących po klasie Publication z int na obiekty typu Year i MonthDay





