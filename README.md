# Projekt Programowanie Obiektowe
Project nr 1 na zajęcia z Programowania Obiektowego. Autor: Marcin Świątkowski, Informatyka Data Science

Krótki opis zadania: stworzyć symulator środowiska, w którym żyją, rozmnażają się i umierają zwierzęta.

To, co udało mi się zrobić (w nawiasach podane są nazwy plików i fragmentów kodu odpowiedzialnych za daną implementację):
+ utworzone menu pozwalające ustawić: rozmiar mapy, ilość zwierząt, ilość roślin, ilość energii startowej, wielkość kosztu ruchu, ilość energii z pożywienia (<code>App.java</code>)
+ utworzone menu zawiera przycisk start, który przełącza na scenę simulation oraz generuje mapę (<code>App.java</code>)
+ dwie sceny: menu, które wyświetlane jest najpierw oraz simulation wyświetlające symulację (<code>App.java</code>)
+ w scenie menu w polach tekstowych są już sugerowane wartości (<code>App.java</code>)
+ w scenie simulation tworzone są dwie mapy (w zamierzeniu jedna z nich miałą być tą zawijaną, a druga tą z murem) (<code>App.java</code>)
+ mapy tworzone są w oparciu o liczby wpisane w pola tekstowe w scenie menu (<code>App.java</code> oraz inne miejsca w programie, które korzystają z tych pól, np. metoda <code>run()</code> w <code>SimEngine.java</code>)
+ w scene simulation dwa przyciski: Return (cofające do sceny menu i resetujące zawartość mapy) oraz Exit (kończące program) (<code>App.java</code>)
+ metoda tworząca mapy zwierząt i roślin (<code>GridCreator.java</code>)
+ każda pozycja ma inny odpowiednik w obrazku zwierzaka (<code>Animal.java</code>, metoda <code>toString()</code>)
+ pozycji jest 8, powiązane są z ruchami (genami) (ruchów jest 7, jeden "ruch", czyli 0, oznacza pozostanie w miejscu) (<code>MapDir.java</code> oraz <code>Animal.java</code> metoda <code>move()</code>)
+ w momencie stworzenia zwierzęta uruchamiany jest oddzielny wątek. Tak samo w przypadku trawy (<code>Animal.java</code> metoda <code>run()</code>, <code>Grass.java</code> metoda <code>run()</code>, a samo uruchomienie wątku jest w <code>SimEngine.java</code> w metodach <code>AddAnimal()</code> oraz <code>AddGrass()</code>)
+ trawa umiejscawiana jest w losowych miejscach (<code>Grass.java</code>)
+ w obszarze obliczanym w oparciu o <code>jungleSize</code> trawa tworzona jest podwójnie każdego dnia (<code>Grass.java</code> metoda <code>run()</code>)
+ z każdym ruchem zwierze "głoduje" - to znaczy, że każdy ruch generuje koszt energii (<code>Animal.java</code> metoda <code>starving()</code>)
+ przy napotkaniu (pokryciu się koordynatów) rośliny i zwierzęcia, energia zwierzęcia wzrasta o wskazaną w menu wartość, zaś kępka trawy zostaje usunięta (<code>Animal.java</code> metoda <code>eatGrass()</code>)
+ po spadku energii do 0 zwierzę jest usuwane (<code>Animal.java</code> metoda <code>starving()</code>)
+ rozmnażanie - gdy pozycje zwierząt się pokryją zostaje wywołana funkcja tworząca nowe zwierzę. Energia rodziców maleje o 1/4 (<code>Animal.java</code> metoda <code>breeding()</code>)


Niestety nie udało mi się zrobić animacji. Coś było nie tak z wątkami i mapy nie chciały się aktualizować. Poruszanie się zwierząt, rozmnażanie i jedzenie (a także umieranie) odbywa się jednak cały czas w tle. Czasem jednak jest problem z poruszaniem się i zwierzęta potrafią zatrzymać się w miejscu.
Nie ma również statystyk, w tym o rodzicach i zapisu do pliku csv.
