==============
Problema Frati
==============

CERINTA

În orasul Game City se organizează o serie de concursuri sportive. Doi frati,
Sam si Jon sunt pasionati de sport si vor să participe la toate concursurile, însă la
acelasi concurs nu pot participa mai multi membri din aceeasi familie.
La fiecare concurs, câstigătorul primeste ca premiu fie un număr de jocuri video
(jocuri i ), fie un (alt) număr de benzi desenate (benzi i ).
Jon adoră jocurile video si detestă benzile desenate, iar Sam tocmai invers si de
aceea fiecare ar vrea că la finalul concursurilor să aibă mai multe obiecte preferate
decât fratele său si diferenta dintre numărul de obiecte preferate si numărul de
obiecte detestate să fie cât mai mare, iar, dacă nu poate avea mai multe obiecte
preferate decât fratele său, diferenta dintre numărul de obiecte detestate si numărul
de obiecte preferate să fie cât mai mică.
În cazul în care există mai multe posibilităti ca la final să fie aceeasi diferentă,
fiecare va dori să aibă cât mai multe obiecte preferate.
Înainte de începerea înscrierilor, cei doi stabilesc la ce concursuri să meargă
fiecare astfel: fiecare alege un concurs până când nu mai rămâne niciun concurs de
ales, iar Jon face primul alegerea, pentru că este mai fratele mai mare. De asemenea,
fiind foarte încrezători în aptitudinile lor, ei fac alegerile în ipoteza că vor câstiga concursurile la care participă.


IMPLEMENTARE

Algoritmul folosit de mine este urmatorul: 
	Am sortat descrescator concursurile dupa suma dintre numarul de jocuri si numarul de benzi ale fiecarui concurs, iar in caz ca sumele erau egale, sortarea se face descrescator dupa numarul de jocuri (obiecte preferate ale lui Jon).

	Apoi se parcurge fiecare concurs in parte si se alege jucatorul astfel:
	Daca numarul concursului este divizibil cu 2, este randul lui Jon, altfel este randul lui Sam. Cand alege Jon, el va alege primul concurs pe care il gaseste la pozitia respectiva. Sam va alege, in caz ca 2 sau mai multe,concursuri au aceeasi suma, ultimul concurs ce are suma respectiva. Astfel, se garanteaza respectarea tuturor conditiilor din cerinta pentru alegerea concursurilor de catre cei doi participanti.

Complexitatea temporala a algoritmului folosit este O(nlogn + n^2), fapt pentru care rezultatele ultimelor doua teste sunt corecte, dar nu se incadreaza in limita de timp.


====================
Problema Numaratoare
====================

CERINTA

Într-o zi de primăvară, după ce au rezolvat toate problemele propuse, Paula si
Pavel au găsit un nou joc la care să se gândească. Fiind date două numere, s (suma)
si n (numărul de pozitii), ei găsesc că există mai multe moduri în care pot exprima s, ca sumă de n numere întregi, strict pozitive.


IMPLEMENTARE

Algoritmul folosit de mine este urmatorul:
	Implementarea se face folosind backtracking pentru a gasi toate modurile de a descompune numarul s in suma de numere intregi, strict pozitive. Daca solutia este valida(lungimea solutiei == n), se adauga intr-un ArrayList de solutii si se incrementeaza un contor de retine numarul de solutii gasite.
	Dupa completarea intregului ArrayList, acesta se sorteaza descrescator conform criteriului din cerinta(dupa fiecare numar din solutie). La final, se alege solutia de pe pozitia i si se creaza String-ul ce va fi scris in fisierul de output. In caz ca pozitia i este mai mare decat numarul de solutii gasite, se va afisa '-'.

Complexitatea temporala a acestui algoritm este O(nlogn + 2^n), fapt pentru care doar primele 4 teste se incadreaza in limita de timp impusa.