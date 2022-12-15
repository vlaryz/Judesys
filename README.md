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
