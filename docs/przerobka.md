Zamieniłem sposoby obsługi zdarzeń:
1. Przycisk "Zgadnij": Lambda -> Aktywność jako listener
2. Przycisk "Od nowa": Wspólny listener z getId() -> android:onClick w pliku XML
3. Przycisk "Poddaj się": Wspólny listener z getId() -> Lambda

Porównanie:
Pierwsza wersja (Lambda + Wspólny listener z getId()) była krótsza i łatwiejsza w utrzymaniu. Rozbudowa o 5 nowych przycisków w pierwszej wersji wymagałaby jedynie dodania ich do wspólnego listenera w jednym miejscu (`if/else`) i dopisania jednej linijki podpinającej listener. W nowej wersji (gdzie rozbiliśmy to na Aktywność jako Listener, XML onClick i Lambdę) kod stał się dużo bardziej rozstrzelony i niekonsekwentny. Rozbudowa wymagałaby dopisywania osobnych lambd, rozbudowywania głównego `onClick` z aktywności lub dopisywania w plikach XML kolejnych atrybutów `onClick`, co utrudniłoby czytelność i zarządzenie.
