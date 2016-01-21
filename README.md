Collector
=========

Semesterarbeit von Mathias Weigert

Betreuungsperson: M. Reiser  
Letzter Abgabetermin: 5. Mai 2016

##Aufgabe

###Zusammenfassung
Management von Sammlungen im Bereich: Bücher, Filme (DVD & Blue Ray) 
und Computer-/Konsolenspielen durch eine Android App.

###Ausgangslage
Ich besitze (sehr) viele Bücher, Filme und Spiele (Computer & Konsolen). 
Seit einiger Zeit passiert es immer häufiger, dass ich auf Flohmärkten 
und in Brockenhäusern Fehlkäufe tätige und ich beim Einsortieren meiner 
neuen Schätze feststelle, dass ich dieses spezielle Buch, Film oder Spiel 
bereits besitze. Vor ein paar Jahren hatte ich eine Software mit Barcodescanner 
für meinen PC (http://intelliscanner.com/products/media/index.html), welche 
meine Sammlung auch online zur Verfügung stellte. Als ich versuchte die 
Datenbank von Windows XP auf Windows 7 zu migrieren, traten einige Probleme 
auf. Ich machte mich auf die Suche nach Alternativen im Bereich Apps für mein 
Smartphone, da ich in anderen Bereichen bereits gute Software für die Verwaltung 
meiner Sammlungen (z.B. Magic the Gathering) gefunden hatte. Bisher konnte ich 
noch keine App finden, welche all meine Ansprüche vereint.

###Ziel der Arbeit
Eine funktionierende App, welche mittels der eingebauten Kamera (als Barcodescanner) 
das Buch, den Film oder das Spiel identifiziert und einer Sammlung hinzufügt. Die 
Items der Sammlung sollen vom Benutzer verwaltet werden können. Eine weitere 
Funktion soll das Verleihen einzelner Items ermöglichen, so dass der User die 
maximale Kontrolle über seine Sammlung hat.

###Aufgabenstellung
1. Recherche  
Herausfinden, was es momentan auf dem Markt (Google Play / Amazon App Market) 
im Bereich der Apps gibt, welche helfen Sammlungen im Bereich Bücher, DVD und 
Konsolen-Spiele zu verwalten. Überblick erstellen über die einzelnen Apps und 
Ihre Fähigkeiten.
2. Ist-Analyse  
Momentan existiert noch keine vergleichbare App. Alle existierenden Apps können 
nur Teile der geplanten gesamten Funktionalität der Collector App abdecken.
3. Anforderungsanalyse  
Collector soll eine App sein, welche für folgende Medientypen: Bücher, DVD und 
Computer- oder Konsolenspiele den Besitzer beim Verwalten seiner Sammlung 
unterstützt. Egal welchem Medientyp ein Item angehört, können mit der App folgende 
Aktionen durchgeführt werden können.  
* Anlegen eines neuen Items  
* Ausgabe aller Items (inklusive Filterfunktion)  
* Bearbeiten eines Items  
* Löschen eines Items  
* Exportieren aller Items (inklusive Filterfunktion)  
* Verwalten verliehener Items  
* Speichern der Items (in einer Datenbank)  
4. Konzept  
Um das Anlegen einzelner Items zu erleichtern, soll die App mittels der Kamera den 
Barcode des Items scannen und einen Abgleich mit verfügbaren Internetdatenbanken 
in den Bereichen der einzelnen Medientypen durchführen. Fehlende oder fehlerhafte 
Daten bei einzelnen Items soll der Benutzer manuell eingeben bzw. anpassen können.  
Die Ausgabe der Items einer Sammlung soll entweder visuell (auf dem Bildschirm) oder 
per Datenexport (CSV oder XML) erfolgen. Ein Filter, welchen die App zur Verfügung 
stellt, kann genutzt werden um die Ausgabe der Items einzuschränken.
Eine Verleihverwaltung rundet die App ab und hilft dem User nicht den Überlick über verliehene Items seiner Sammlung zu verlieren.
5. Prototyp  
Der Prototyp der App soll alle Funktionen, welche in Punkt 4. definiert wurden, 
fehlerfrei ausführen können. Die App soll auf möglichst vielen Android Geräten 
funktionieren.
6. Testing  
Das Testing wird durch das anlegen, möglichst vieler Unterschiedlicher Items aller 
Medientypen, erfolgen. Die einzelnen Items werden dokumentiert und auftretende 
Schwierigkeiten beim Anlegen der Items werden ebenfalls dokumentiert. Sobald ein 
gewisser Grundstock an Items in der Datenbank hinterlegt sind, werden die restlichen 
Funktionen getestet.

### Erwartete Resultate
1. Recherche  
Gründlicher Marktüberblick, welcher die Apps übersichtlicht Darstellt, welche ähnliche 
oder sogar identische Funktionen wie die App Collector besitzen. Nach der kurzen 
Vorstellung der einzelnen Apps, wird ein Überblick alle recherchierten Apps im Vergleich 
zu Collector zeigen.
2. Ist-Analyse  
Es wird anhand der Funktionen von Collector dargestellt, das es keine der existierenden 
Apps den selben Funktionsumfang haben wie Collector.
3. Anforderungsanalyse  
Alle beschriebenen Funktionalitäten sollen fehlerfrei in der App umgesetzt werden.
4. Konzept  
Die eingesetzten Werkzeuge und Konzepte sollen dokumentiert werden. Einzelne für die 
Funktion der App wichtige Klassen und Funktionen sollen detailiert dargestellt werden. 
Design-Entscheidungen sollen erklärt werden.
5. Prototyp  
Der Prototyp soll mindestens auf dem Test-Smartphone (Samsung Galaxy XCover) fehlerfrei 
funktionieren.
6. Testing  
Sowohl die automatisierten als auch die manuellen Tests sollen ausführlich, klar und 
komplett dokumentiert werden.

##Version
 15. Dez. 2014 - V0.1 Aufgabe muss nochmals genauer definiert werden.
 04. Nov. 2015 - Freigabe der Aufgabe
