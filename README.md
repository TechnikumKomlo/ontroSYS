# OntroSYS
**fájlkezelő alkalmazás**

Üdvözöljük az OntroSYS projekt oldalán! Ez a szoftver egy robusztus megoldást kínál fájlkezelési feladatok ellátására.

# Letöltés

1.  **Hivatalos Weboldal (Ajánlott):** A leggyorsabb és legkényelmesebb letöltéshez látogasson el a **[akos0328.webredirect.org](http://akos0328.webredirect.org)** oldalra.
2.  **GitHub Releases:** Amennyiben a weboldal nem elérhető, a stabil, tesztelt változatokat a jobb oldali [Releases](https://github.com) menüpont alatt, a legfrissebb (Latest) jelölésű verziónál találja meg.
3.  **Teljes Archívum:** Mivel bizonyos bináris állományok meghaladják a GitHub 25 MB-os korlátját, a minden erőforrást tartalmazó teljes verzió az alábbi külső tárhelyről is elérhető:
    *   **[OntroSYS Teljes Verzió (OneDrive)](https://1drv.ms/f/c/952a6ce2369afcd1/IgAdlztNMyArTIufaf--vORDAadEVVAXulLKiC3Kq3DzAzo?e=CK7HyX)**

# Fontos figyelmeztetés és felelősségkorlátozás
A forráskódban található fejlesztői (dev) verzió nem tekinthető stabilnak.

Felelősség: A szoftver használatából eredő esetleges adatvesztésért vagy károkért a fejlesztők nem vállalnak felelősséget.

Biztonság: A fejlesztői kiadás futtatása és tesztelése kizárólag izolált, virtuális környezetben (VM) javasolt.

Felhasználói verzió: Általános használatra kérjük, válassza az előre csomagolt, telepítővel ellátott Release kiadásokat, amelyek alapos tesztelésen estek át és stabil működést biztosítanak.

# Fejlesztői környezet és futtatás
Amennyiben a forráskódból szeretné építeni vagy futtatni a projektet, az alábbi környezeti beállítások szükségesek:

**Előfeltételek**

*os: Windows 10/11 ; Ubuntu LTS 22.04.5>*

*IDE: Visual Studio Code (ajánlott)*

*Build eszköz: Gradle*

*Runtime: * Java JDK 21*

*Java JDK 24 (szükséges a teljes körű kompatibilitáshoz)*

**Futtatás folyamata**

Csakis a v2.x.x verzióval ellátott projektek.

Nyissa meg a projekt gyökérkönyvtárát egy terminálban.

*Futtassa az alábbi parancsot az alkalmazás indításához:*

./gradlew run
