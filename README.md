# Judesys
Projekto tikslas – palengvinti visų renginių bilietų pirkimų/pardavimų procesą.
Veikimo principas – pačią kuriamą platformą sudaro dvi dalys: internetinė aplikacija, kuria 
naudosis žmonės, norintys įsigyti bilietus į renginius, bei aplikacijų programavimo sąsaja (angl. 
trump. API).
* Sistemos administratoriai gali įdėti miestą, renginį, visą informaciją apie jį, pvz: renginio aprašymą, 
bilieto kaina ir t.t. 
* Neprisijungę naudotojai galės peržiūrėti renginius juos dominančiam mieste ir gauti informaciją 
apie juos.
* Prisijungę naudotojai papildomai galės įsigyti bilietus ir juos peržiūrėti.

# Sistemos sudedamosios dalys
* Kliento pusė (ang. Front-End) – naudojant Angular
* Serverio pusė (angl. Back-End) – naudojant Java Spring Boot. Duomenų bazė – MySQL


# Pirmas laboratoinis darbas
Buvo sukurti endpoint'ai visiems trim objektams (City, Event, Ticket).
Endpointai atitinka visus REST reikalavimus, t.y. response codes, URL ir t.t.
Endpointai sukurti visoms CRUD operacijoms

# Antras laboratoinis darbas
Buvo sukurta autentikacija ir autorizacija. Kiekvienas prisijunges naudotojas gauna token'ą kaip atsaką iš serverio po sėkmingos autentikacijos.
Kiekviena užklausa į serverį turi turėti savyje gautą iš serverio token'a. Autorizacija įvyksta remiantis roliū sistema.
Kodas buvo patalpintas ir paleistas Azure debesyje.

# Trečias laboratoinis darbas
Aplikacijai buvo sukurtas frontend'as, kuris yra realizuotas su Angular framework.
Frontendas buvo panaudotas AngularMaterials biblioteka. Yra Repsonsive layout, animacijos, paveiksliukai, modaliniai langai ir viskas ko buvo reikalaujama lab. darbo apraše.
Taip pat yra ir dokumentacija

# Wireframes ir rezultatas
## Home puslapis
![homePage](https://user-images.githubusercontent.com/61593050/208077296-1b94d6ac-c1f7-437f-9ee7-ca05bf46aab4.png)
![image](https://user-images.githubusercontent.com/61593050/208077405-7e77698b-05a1-41e2-ab19-3df877dca0a1.png)

## Registracijos puslapis
![registerPage](https://user-images.githubusercontent.com/61593050/208077560-c558a521-236a-467d-abc8-9cce1595b6cc.png)
![image](https://user-images.githubusercontent.com/61593050/208077629-4575ed73-66e7-43e9-91b9-77522a91b6dd.png)


## Prisijungimo puslapis
![loginPage](https://user-images.githubusercontent.com/61593050/208077711-69b4f2d1-877d-489b-a0c9-563d62d2d5b4.png)
![image](https://user-images.githubusercontent.com/61593050/208077759-c429bba2-3650-45f8-9b08-08947cc7c30e.png)

## Miestų sąrašo puslapis
![citiesPage](https://user-images.githubusercontent.com/61593050/208077862-1a31c3b9-2cbe-4549-ac13-7611e4699819.png)
![image](https://user-images.githubusercontent.com/61593050/208077967-555394a2-ff1a-4bfd-ba3e-938a3b2d8fa7.png)

## Miesto renginių puslapis
![eventsListPage](https://user-images.githubusercontent.com/61593050/208078133-bc8541e2-0cbf-489e-bba3-7665b66fb9a1.png)
![image](https://user-images.githubusercontent.com/61593050/208078097-de0c41ae-97a7-4645-9873-456f5506007a.png)

## Renginių detalių puslapis
![cityDetailsPage](https://user-images.githubusercontent.com/61593050/208078266-6e42b72e-54c6-413f-83f5-1638a70c464d.png)
![image](https://user-images.githubusercontent.com/61593050/208078328-cbad671a-5db2-4819-b9f2-b4b40d665b26.png)

## Renginio bilietų puslapis
![buyTicketsPage](https://user-images.githubusercontent.com/61593050/208078429-9afb82b8-c180-45cf-9f47-035c31a9f217.png)
![image](https://user-images.githubusercontent.com/61593050/208078454-c853ebe9-8664-4293-88f4-ee8d08e585bb.png)

## Renginio bilietų pirkimo langas
![ticketPurchasePage](https://user-images.githubusercontent.com/61593050/208078534-11317a86-b14e-4b74-a31d-ccf16c841666.png)
![image](https://user-images.githubusercontent.com/61593050/208078650-5d7966a1-bfcb-4b91-8095-c375661dabb2.png)

## Paskyros valdymo ir pirkimų istorijos langas
![accountPage](https://user-images.githubusercontent.com/61593050/208078756-da978151-d048-4ee3-b72b-56418861b4da.png)
![image](https://user-images.githubusercontent.com/61593050/208078794-ec303fb4-2192-4da6-8ea8-909ad4435424.png)

## Administravimo langas
![adminPanelPage](https://user-images.githubusercontent.com/61593050/208078939-370aaca3-634f-43d3-9d80-832c0af1d3d3.png)
![image](https://user-images.githubusercontent.com/61593050/208078965-80c032f9-3448-42ec-9674-01ea94bda362.png)

## Dokumentacijos langas
![image](https://user-images.githubusercontent.com/61593050/208079023-a404c3fc-2f38-462e-9b41-94dca17acade.png)





