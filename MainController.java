package com.mycompany.ehks;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;

import java.io.File;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class MainController {

    private final TextField txtName = new TextField();
    private final TextField txtVorname = new TextField();
    private final DatePicker dpGeburtsdatum = new DatePicker();
    private final TextField txtVersichertenId = new TextField();
    private final TextField txtStrasse = new TextField();
    private final TextField txtHausnummer = new TextField();
    private final TextField txtPlz = new TextField();
    private final TextField txtOrt = new TextField();
    private final ComboBox<String> cbVersichertenArt = new ComboBox<>();
    private final ComboBox<String> cbGeschlecht = new ComboBox<>();
    private final TextField txtBetriebsstaettenNr = new TextField();
    private final TextField txtArztnummer = new TextField();
    private final TextField txtArztname = new TextField();
    private final TextField txtFilterName = new TextField();

    private final DatePicker dpUntersuchungsdatum = new DatePicker();

    private final CheckBox chkVerdachtsdiagnoseND = new CheckBox("Verdachtsdiagnose");
    private final CheckBox chkMalignesMelanom = new CheckBox("Malignes Melanom");
    private final CheckBox chkBasalzellkarzinom = new CheckBox("Basalzellkarzinom");
    private final CheckBox chkSpinozellulaeresKarzinom = new CheckBox("Spinozelluläres Karzinom");
    private final CheckBox chkAndererHautkrebs = new CheckBox("Anderer Hautkrebs");
    private final CheckBox chkSonstigerDermatologischerBefund = new CheckBox("Sonstiger dermatologisch abklärungsbedürftiger Befund");
    private final CheckBox chkUeberweisungAnDermatologen = new CheckBox("Screening-Teilnehmer wird an Dermatologen überwiesen");

    private final CheckBox chkGesundheitsuntersuchung = new CheckBox("Gesundheitsuntersuchung (GU) durchgeführt");

    private final CheckBox chkUeberweisungImRahmenHKS = new CheckBox("Patient kommt auf Überweisung im Rahmen HKS");
    private final CheckBox chkUeberweisenderArztHatHKS = new CheckBox("Überweisender Arzt hat HKS durchgeführt");

    private final CheckBox chkAngabeUeberweisenderArzt = new CheckBox("Angabe über Verdachtsdiagnose liegt vor");
    private final CheckBox chkUeberweisenderArztMalignesMelanom = new CheckBox("Malignes Melanom");
    private final CheckBox chkUeberweisenderArztBasalzellkarzinom = new CheckBox("Basalzellkarzinom");
    private final CheckBox chkUeberweisenderArztSpinozellulaeresKarzinom = new CheckBox("Spinozelluläres Karzinom");
    private final CheckBox chkUeberweisenderArztAndererHautkrebs = new CheckBox("Anderer Hautkrebs");

    private final CheckBox chkDermatologeVerdachtsdiagnose = new CheckBox("Verdachtsdiagnose");
    private final CheckBox chkDermatologeMalignesMelanom = new CheckBox("Malignes Melanom");
    private final CheckBox chkDermatologeBasalzellkarzinom = new CheckBox("Basalzellkarzinom");
    private final CheckBox chkDermatologeSpinozellulaeresKarzinom = new CheckBox("Spinozelluläres Karzinom");
    private final CheckBox chkDermatologeAndererHautkrebs = new CheckBox("Anderer Hautkrebs");
    private final CheckBox chkDermatologeSonstigerBiopsieBefund = new CheckBox("Sonstiger mit Biopsie abklärungsbedürftiger Befund");

    private final CheckBox chkBiopsieOderExzision = new CheckBox("Biopsie/Exzision durchgeführt");
    private final TextField txtAnzahlBiopsien = new TextField();
    private final CheckBox chkAnderweitigeTherapie = new CheckBox("Anderweitige Therapie/Diagnostik");
    private final CheckBox chkKeineWeitereTherapie = new CheckBox("Keine weitere Therapie/Diagnostik");

    private final CheckBox chkHistopathologieMalignesMelanom = new CheckBox("Malignes Melanom");
    private final ComboBox<String> cbMelanomKlassifikation = new ComboBox<>();
    private final ComboBox<String> cbMelanomBreslow = new ComboBox<>();
    private final CheckBox chkHistopathologieBasalzellkarzinom = new CheckBox("Basalzellkarzinom");
    private final TextField txtBasalzellHorizontal = new TextField();
    private final TextField txtBasalzellVertikal = new TextField();
    private final CheckBox chkHistopathologieSpinozellulaeresKarzinom = new CheckBox("Spinozelluläres Karzinom");
    private final ComboBox<String> cbSpinozellulaeresKlassifikation = new ComboBox<>();
    private final ComboBox<String> cbSpinozellulaeresGrading = new ComboBox<>();
    private final CheckBox chkHistopathologieAndererHautkrebs = new CheckBox("Anderer Hautkrebs");
    private final CheckBox chkHistopathologieAtypischerNaevus = new CheckBox("Atypischer Nävuszellnävus");
    private final CheckBox chkHistopathologieJunktionalerNaevus = new CheckBox("Junktionaler/Compound/Dermaler atypischer Nävuszellnävus");
    private final CheckBox chkHistopathologieAktinischeKeratose = new CheckBox("Aktinische Keratose");
    private final CheckBox chkHistopathologieAndereVeränderung = new CheckBox("Andere hier nicht relevante Hautveränderung");

    private final TableView<Patient> patientTable = new TableView<>();
    private final TableView<ScreeningData> screeningTable = new TableView<>();
    private final Label statusLabel = new Label("Bereit");

    private Patient currentPatient = null;
    private ObservableList<Patient> patientList = FXCollections.observableArrayList();
    private ObservableList<ScreeningData> screeningList = FXCollections.observableArrayList();
    private FilteredList<Patient> filteredPatients = new FilteredList<>(patientList);

    private final Button btnSave = new Button("Speichern");
    private boolean isEditingPatient = false;
    private boolean isEditingScreening = false;

    public MainController() {
        initCombos();
        setupPatientForm();
        setupScreeningForm();
        setupTables();
        setupEventHandlers();
    }

    private void initCombos() {
        cbVersichertenArt.setItems(FXCollections.observableArrayList("1", "3", "5"));
        cbVersichertenArt.getSelectionModel().selectFirst();
        cbGeschlecht.setItems(FXCollections.observableArrayList("M", "F", "UN"));
        cbGeschlecht.getSelectionModel().selectFirst();

        cbMelanomKlassifikation.setItems(FXCollections.observableArrayList("in situ", "invasiv"));
        cbMelanomBreslow.setItems(FXCollections.observableArrayList("≤ 1 mm", "1,01-2 mm", "2,01-4 mm", "> 4 mm"));
        cbSpinozellulaeresKlassifikation.setItems(FXCollections.observableArrayList("in situ", "invasiv"));
        cbSpinozellulaeresGrading.setItems(FXCollections.observableArrayList("Gx", "G1", "G2", "G3", "G4"));
    }

    private void setupEventHandlers() {
        txtFilterName.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredPatients.setPredicate(patient -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();
                return patient.getName().toLowerCase().contains(lowerCaseFilter)
                        || patient.getVorname().toLowerCase().contains(lowerCaseFilter);
            });
        });

        chkVerdachtsdiagnoseND.selectedProperty().addListener((obs, oldVal, newVal) -> {
            chkMalignesMelanom.setDisable(!newVal);
            if (!newVal) {
                chkMalignesMelanom.setSelected(false);
            }
        });

        chkBiopsieOderExzision.selectedProperty().addListener((obs, oldVal, newVal) -> {
            txtAnzahlBiopsien.setDisable(!newVal);
            if (!newVal) {
                txtAnzahlBiopsien.clear();
            }
        });

        chkHistopathologieBasalzellkarzinom.selectedProperty().addListener((obs, oldVal, newVal) -> {
            txtBasalzellHorizontal.setDisable(!newVal);
            txtBasalzellVertikal.setDisable(!newVal);
            if (!newVal) {
                txtBasalzellHorizontal.clear();
                txtBasalzellVertikal.clear();
            }
        });
    }

    private VBox setupPatientForm() {
        VBox form = new VBox(10);
        form.setPadding(new Insets(10));

        Button btnSave = new Button("Patient speichern");
        btnSave.setOnAction(e -> savePatient());

        form.getChildren().addAll(
                new Label("Patientendaten"),
                createRow("Name:", txtName),
                createRow("Vorname:", txtVorname),
                createRow("Geburtsdatum:", dpGeburtsdatum),
                createRow("Versicherten-ID:", txtVersichertenId),
                createRow("Straße:", txtStrasse),
                createRow("Nr.:", txtHausnummer),
                createRow("PLZ:", txtPlz),
                createRow("Ort:", txtOrt),
                createRow("Versichertenart:", cbVersichertenArt),
                createRow("Geschlecht:", cbGeschlecht),
                new Separator(),
                new Label("Arztpraxis"),
                createRow("Betriebsstättennr.:", txtBetriebsstaettenNr),
                createRow("Arztnummer:", txtArztnummer),
                createRow("Arztname:", txtArztname),
                new Separator(),
                btnSave
        );
        return form;
    }

    private VBox setupScreeningForm() {
        VBox form = new VBox(10);
        form.setPadding(new Insets(10));

        Button btnSave = new Button("Untersuchung speichern");
        btnSave.setOnAction(e -> saveScreening());

        form.getChildren().addAll(
                new Label("Untersuchungsdaten"),
                createRow("Untersuchungsdatum:", dpUntersuchungsdatum),
                new Label("Verdachtsdiagnose"),
                chkVerdachtsdiagnoseND,
                new HBox(10, chkMalignesMelanom, chkBasalzellkarzinom, chkSpinozellulaeresKarzinom, chkAndererHautkrebs, chkSonstigerDermatologischerBefund),
                chkUeberweisungAnDermatologen,

                new Label("Gesundheitsuntersuchung"),
                chkGesundheitsuntersuchung,
                new Label("Überweisung im Rahmen HKS"),
                chkUeberweisungImRahmenHKS,
                chkUeberweisenderArztHatHKS,
                new Label("Angabe der Verdachtsdiagnose des überweisenden Arztes"),
                chkAngabeUeberweisenderArzt,
                new HBox(10, chkUeberweisenderArztMalignesMelanom, chkUeberweisenderArztBasalzellkarzinom,
                        chkUeberweisenderArztSpinozellulaeresKarzinom, chkUeberweisenderArztAndererHautkrebs),
                new Label("Verdachtsdiagnose des Dermatologen"),
                chkDermatologeVerdachtsdiagnose,
                new HBox(10, chkDermatologeMalignesMelanom, chkDermatologeBasalzellkarzinom,
                        chkDermatologeSpinozellulaeresKarzinom, chkDermatologeAndererHautkrebs, chkDermatologeSonstigerBiopsieBefund),
                new Label("Biopsie/Exzision"),
                chkBiopsieOderExzision,
                createRow("Anzahl Biopsien:", txtAnzahlBiopsien),
                new HBox(10, chkAnderweitigeTherapie, chkKeineWeitereTherapie),
                new Label("Histopathologie"),
                chkHistopathologieMalignesMelanom,
                createRow("Klassifikation:", cbMelanomKlassifikation),
                createRow("Tumordicke (Breslow):", cbMelanomBreslow),
                chkHistopathologieBasalzellkarzinom,
                createRow("Horizontaler Durchmesser (mm):", txtBasalzellHorizontal),
                createRow("Vertikaler Durchmesser (mm):", txtBasalzellVertikal),
                chkHistopathologieSpinozellulaeresKarzinom,
                createRow("Klassifikation:", cbSpinozellulaeresKlassifikation),
                createRow("Grading:", cbSpinozellulaeresGrading),
                new HBox(10, chkHistopathologieAndererHautkrebs, chkHistopathologieAtypischerNaevus,
                        chkHistopathologieJunktionalerNaevus, chkHistopathologieAktinischeKeratose, chkHistopathologieAndereVeränderung),
                new Separator(),
                btnSave
        );
        return form;
    }

    private void setupTables() {
        patientTable.setItems(filteredPatients);
        patientTable.setPlaceholder(new Label("Keine Patienten vorhanden."));

        TableColumn<Patient, String> colName = new TableColumn<>("Name");
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colName.setSortType(TableColumn.SortType.ASCENDING);
        colName.setComparator((p1, p2) -> {
            if (p1 == null && p2 == null) {
                return 0;
            }
            if (p1 == null) {
                return 1;
            }
            if (p2 == null) {
                return -1;
            }
            return p1.compareTo(p2);
        });

        TableColumn<Patient, String> colVorname = new TableColumn<>("Vorname");
        colVorname.setCellValueFactory(new PropertyValueFactory<>("vorname"));
        colVorname.setComparator((v1, v2) -> {
            if (v1 == null && v2 == null) {
                return 0;
            }
            if (v1 == null) {
                return 1;
            }
            if (v2 == null) {
                return -1;
            }
            return v1.compareTo(v2);
        });

        TableColumn<Patient, String> colId = new TableColumn<>("ID");
        colId.setCellValueFactory(new PropertyValueFactory<>("versichertenId"));
        colId.setComparator((id1, id2) -> {
            if (id1 == null && id2 == null) {
                return 0;
            }
            if (id1 == null) {
                return 1;
            }
            if (id2 == null) {
                return -1;
            }
            return id1.compareTo(id2);
        });

        TableColumn<Patient, String> colGeburtsdatum = new TableColumn<>("Geburtsdatum");
        colGeburtsdatum.setCellValueFactory(cellData
                -> new javafx.beans.property.SimpleStringProperty(
                        cellData.getValue().getGeburtsdatumString()
                ));
        colGeburtsdatum.setComparator((s1, s2) -> {
            LocalDate d1 = s1 == null || s1.isEmpty() ? null : LocalDate.parse(s1);
            LocalDate d2 = s2 == null || s2.isEmpty() ? null : LocalDate.parse(s2);
            if (d1 == null && d2 == null) {
                return 0;
            }
            if (d1 == null) {
                return 1;
            }
            if (d2 == null) {
                return -1;
            }
            return d1.compareTo(d2);
        });

        patientTable.getColumns().addAll(colName, colVorname, colId, colGeburtsdatum);

        screeningTable.setItems(screeningList);
        screeningTable.setPlaceholder(new Label("Keine Screenings vorhanden."));

        TableColumn<ScreeningData, String> colPatientName = new TableColumn<>("Patient");
        colPatientName.setCellValueFactory(cellData
                -> new javafx.beans.property.SimpleStringProperty(cellData.getValue().getPatientName()));
        colPatientName.setComparator((s1, s2) -> {
            if (s1 == null && s2 == null) {
                return 0;
            }
            if (s1 == null) {
                return 1;
            }
            if (s2 == null) {
                return -1;
            }
            return s1.compareTo(s2);
        });

        TableColumn<ScreeningData, String> colDatum = new TableColumn<>("Datum");
        colDatum.setCellValueFactory(cellData
                -> new javafx.beans.property.SimpleStringProperty(cellData.getValue().getUntersuchungsdatumString()));
        colDatum.setComparator((s1, s2) -> {
            LocalDate d1 = s1 == null || s1.isEmpty() ? null : LocalDate.parse(s1);
            LocalDate d2 = s2 == null || s2.isEmpty() ? null : LocalDate.parse(s2);
            if (d1 == null && d2 == null) {
                return 0;
            }
            if (d1 == null) {
                return 1;
            }
            if (d2 == null) {
                return -1;
            }
            return d1.compareTo(d2);
        });

        TableColumn<ScreeningData, String> colStatus = new TableColumn<>("Status");
        colStatus.setCellValueFactory(cellData -> {
            String color = cellData.getValue().getStatusColor();
            return new javafx.beans.property.SimpleStringProperty(
                    color.equals("#ffaaaa") ? "Therapie"
                    : color.equals("#ffffaa") ? "Auffällig" : "Normal");
        });
        colStatus.setComparator((s1, s2) -> {
            if (s1 == null && s2 == null) {
                return 0;
            }
            if (s1 == null) {
                return 1;
            }
            if (s2 == null) {
                return -1;
            }

            java.util.Map<String, Integer> map = java.util.Map.of("Normal", 1, "Auffällig", 2, "Therapie", 3);
            return map.getOrDefault(s1, 0).compareTo(map.getOrDefault(s2, 0));
        });
        colStatus.setCellFactory(column -> {
            return new TableCell<ScreeningData, String>() {
                @Override
                protected void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    if (item == null || empty) {
                        setText(null);
                        setStyle("");
                    } else {
                        setText(item);
                        ScreeningData screening = getTableView().getItems().get(getIndex());
                        setTextFill(javafx.scene.paint.Color.web(screening.getStatusColor()));
                    }
                }
            };
        });

        screeningTable.getColumns().addAll(colPatientName, colDatum, colStatus);

        for (TableColumn<?, ?> col : patientTable.getColumns()) {
            col.setSortable(true);
        }
        for (TableColumn<?, ?> col : screeningTable.getColumns()) {
            col.setSortable(true);
        }

        patientTable.getSortOrder().add(colName);
        screeningTable.getSortOrder().add(colPatientName);

        patientTable.setOnMouseClicked(e -> {
            if (e.getClickCount() == 2 && patientTable.getSelectionModel().getSelectedItem() != null) {
                editSelectedPatient();
            }
        });

        screeningTable.setOnMouseClicked(e -> {
            if (e.getClickCount() == 2 && screeningTable.getSelectionModel().getSelectedItem() != null) {
                editSelectedScreening();
            }
        });

        btnDeleteSelectedPatient.setOnAction(e -> deleteSelectedPatient());
btnDeleteSelectedScreening.setOnAction(e -> deleteSelectedScreening());
        btnDeleteSelectedPatient.setDisable(true);
        btnDeleteSelectedScreening.setDisable(true);
        patientTable.getSelectionModel().selectedItemProperty().addListener(
                (obs, oldVal, newVal) -> updateDeleteButtonsState());
        screeningTable.getSelectionModel().selectedItemProperty().addListener(
                (obs, oldVal, newVal) -> updateDeleteButtonsState());

        Button btnExport = new Button("Daten exportieren (CSV)");
        btnExport.setOnAction(e -> exportData());

        Button btnImport = new Button("Daten importieren (CSV)");
        btnImport.setOnAction(e -> importData());

        Button btnGenerateReport = new Button("PDF-Kurzbericht generieren");
        btnGenerateReport.setDisable(true);
        btnGenerateReport.setText("PDF-Kurzbericht (deaktiviert)");

        dataTabContent = new VBox(10,
                new Label("Filter nach Name:"),
                txtFilterName,
                new Label("Gespeicherte Daten:"),
                patientTable,
                btnDeleteSelectedPatient,
                screeningTable,
                btnDeleteSelectedScreening,
                btnExport,
                btnImport,
                btnGenerateReport
        );
        dataTabContent.setPadding(new Insets(10));
    }

    private void updateCurrentPatientFromSelection() {
        Patient selected = patientTable.getSelectionModel().getSelectedItem();
        if (selected != null) {
            currentPatient = selected;
            showStatus("Ausgewählt: " + selected.getPatientName());
        } else {
            currentPatient = null;
            showStatus("Kein Patient ausgewählt");
        }
    }

    private void savePatient() {
        String geb = dpGeburtsdatum.getValue() != null ? dpGeburtsdatum.getValue().toString() : "";
        String versichertenArt = cbVersichertenArt.getValue();
        String geschlecht = cbGeschlecht.getValue();

        if (!Validator.isValidPatient(
                txtName.getText().trim(),
                txtVorname.getText().trim(),
                geb,
                txtVersichertenId.getText().trim(),
                txtStrasse.getText().trim(),
                txtHausnummer.getText().trim(),
                txtPlz.getText().trim(),
                txtOrt.getText().trim(),
                versichertenArt,
                geschlecht,
                txtBetriebsstaettenNr.getText().trim(),
                txtArztnummer.getText().trim(),
                txtArztname.getText().trim()
        )) {
            showStatus("Ungültige Eingaben! Bitte prüfen Sie alle Pflichtfelder.");
            return;
        }

        String versichertenId = txtVersichertenId.getText().trim();
        Optional<Patient> existingOpt = DataStorage.getPatients().stream()
                .filter(p -> versichertenId.equals(p.getVersichertenId()))
                .findFirst();

        Patient patient;

        if (existingOpt.isPresent()) {
            patient = existingOpt.get();
            showStatus("Bearbeite existierenden Patienten: " + patient.getPatientName());
        } else {
            patient = new Patient();
        }

        patient.setName(txtName.getText().trim());
        patient.setVorname(txtVorname.getText().trim());
        patient.setGeburtsdatum(dpGeburtsdatum.getValue());
        patient.setVersichertenId(versichertenId);
        patient.setStrasse(txtStrasse.getText().trim());
        patient.setHausnummer(txtHausnummer.getText().trim());
        patient.setPlz(txtPlz.getText().trim());
        patient.setOrt(txtOrt.getText().trim());
        patient.setVersichertenArt(versichertenArt);
        patient.setGeschlecht(geschlecht);
        patient.setBetriebsstaettenNr(txtBetriebsstaettenNr.getText().trim());
        patient.setArztnummer(txtArztnummer.getText().trim());
        patient.setArztname(txtArztname.getText().trim());

        if (!existingOpt.isPresent()) {
            DataStorage.addPatient(patient);
            patientList.add(patient);
        } else {
            patientList.setAll(DataStorage.getPatients());
        }

        showStatus("✅ Patient gespeichert: " + patient.getPatientName());
        clearPatientForm();
        currentPatient = null;
    }

    private void saveScreening() {
        if (currentPatient == null) {
            showStatus("❌ Kein Patient ausgewählt!");
            return;
        }

        LocalDate Untersuchungsdatum = dpUntersuchungsdatum.getValue();
        Optional<ScreeningData> existingOpt = DataStorage.getScreenings().stream()
                .filter(s -> currentPatient.equals(s.getPatient()) &&
                             Objects.equals(Untersuchungsdatum, s.getUntersuchungsdatum()))
                .findFirst();

        ScreeningData screening;
        boolean isNew = !existingOpt.isPresent();

        if (isNew) {
            screening = new ScreeningData();
            screening.setPatient(currentPatient);
            screening.setUntersuchungsdatum(Untersuchungsdatum);
        } else {
            screening = existingOpt.get();
        }

        screening.setVerdachtsdiagnoseND(chkVerdachtsdiagnoseND.isSelected());
        screening.setMalignesMelanom(chkMalignesMelanom.isSelected());
        screening.setBasalzellkarzinom(chkBasalzellkarzinom.isSelected());
        screening.setSpinozellulaeresKarzinom(chkSpinozellulaeresKarzinom.isSelected());
        screening.setAndererHautkrebs(chkAndererHautkrebs.isSelected());
        screening.setSonstigerDermatologischerBefund(chkSonstigerDermatologischerBefund.isSelected());
        screening.setUeberweisungAnDermatologen(chkUeberweisungAnDermatologen.isSelected());

        screening.setGesundheitsuntersuchung(chkGesundheitsuntersuchung.isSelected());

        screening.setUeberweisungImRahmenHKS(chkUeberweisungImRahmenHKS.isSelected());
        screening.setUeberweisenderArztHatHKS(chkUeberweisenderArztHatHKS.isSelected());

        screening.setAngabeUeberweisenderArzt(chkAngabeUeberweisenderArzt.isSelected());
        screening.setUeberweisenderArztMalignesMelanom(chkUeberweisenderArztMalignesMelanom.isSelected());
        screening.setUeberweisenderArztBasalzellkarzinom(chkUeberweisenderArztBasalzellkarzinom.isSelected());
        screening.setUeberweisenderArztSpinozellulaeresKarzinom(chkUeberweisenderArztSpinozellulaeresKarzinom.isSelected());
        screening.setUeberweisenderArztAndererHautkrebs(chkUeberweisenderArztAndererHautkrebs.isSelected());

        screening.setDermatologeVerdachtsdiagnose(chkDermatologeVerdachtsdiagnose.isSelected());
        screening.setDermatologeMalignesMelanom(chkDermatologeMalignesMelanom.isSelected());
        screening.setDermatologeBasalzellkarzinom(chkDermatologeBasalzellkarzinom.isSelected());
        screening.setDermatologeSpinozellulaeresKarzinom(chkDermatologeSpinozellulaeresKarzinom.isSelected());
        screening.setDermatologeAndererHautkrebs(chkDermatologeAndererHautkrebs.isSelected());
        screening.setDermatologeSonstigerBiopsieBefund(chkDermatologeSonstigerBiopsieBefund.isSelected());

        screening.setBiopsieOderExzision(chkBiopsieOderExzision.isSelected());
        try {
            screening.setAnzahlBiopsien(txtAnzahlBiopsien.getText().isEmpty() ? 0 : Integer.parseInt(txtAnzahlBiopsien.getText()));
        } catch (NumberFormatException e) {
            screening.setAnzahlBiopsien(0);
        }
        screening.setAnderweitigeTherapie(chkAnderweitigeTherapie.isSelected());
        screening.setKeineWeitereTherapie(chkKeineWeitereTherapie.isSelected());

        screening.setHistopathologieMalignesMelanom(chkHistopathologieMalignesMelanom.isSelected());
        screening.setMelanomKlassifikation(cbMelanomKlassifikation.getValue());
        screening.setMelanomBreslow(cbMelanomBreslow.getValue());
        screening.setHistopathologieBasalzellkarzinom(chkHistopathologieBasalzellkarzinom.isSelected());
        try {
            screening.setBasalzellHorizontal(txtBasalzellHorizontal.getText().isEmpty() ? null : Double.parseDouble(txtBasalzellHorizontal.getText()));
        } catch (NumberFormatException e) {
            screening.setBasalzellHorizontal(null);
        }
        try {
            screening.setBasalzellVertikal(txtBasalzellVertikal.getText().isEmpty() ? null : Double.parseDouble(txtBasalzellVertikal.getText()));
        } catch (NumberFormatException e) {
            screening.setBasalzellVertikal(null);
        }
        screening.setHistopathologieSpinozellulaeresKarzinom(chkHistopathologieSpinozellulaeresKarzinom.isSelected());
        screening.setSpinozellulaeresKlassifikation(cbSpinozellulaeresKlassifikation.getValue());
        screening.setSpinozellulaeresGrading(cbSpinozellulaeresGrading.getValue());
        screening.setHistopathologieAndererHautkrebs(chkHistopathologieAndererHautkrebs.isSelected());
        screening.setHistopathologieAtypischerNaevus(chkHistopathologieAtypischerNaevus.isSelected());
        screening.setHistopathologieJunktionalerNaevus(chkHistopathologieJunktionalerNaevus.isSelected());
        screening.setHistopathologieAktinischeKeratose(chkHistopathologieAktinischeKeratose.isSelected());
        screening.setHistopathologieAndereVeränderung(chkHistopathologieAndereVeränderung.isSelected());

        List<String> errors = Validator.getValidationErrors(screening);
        if (!errors.isEmpty()) {
            StringBuilder msg = new StringBuilder("Folgende Plausibilitätsfehler wurden gefunden:\n\n");
            for (String err : errors) {
                msg.append("❌ ").append(err).append("\n");
            }
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Ungültige Eingabe");
            alert.setHeaderText("Untersuchung ist nicht plausibel!");
            alert.setContentText(msg.toString());
            alert.showAndWait();
            return;
        }

        if (isNew) {
            DataStorage.addScreening(screening);
            screeningList.add(screening);
        } else {
            screeningList.setAll(DataStorage.getScreenings());
        }

        showStatus("✅ Untersuchung " + (isNew ? "gespeichert" : "bearbeitet") + " für: " + currentPatient.getPatientName());
        clearScreeningForm();
        currentPatient = null;
    }

    private void exportData() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Export: CSV-Dateien speichern");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("CSV-Dateien", "*.csv"),
                new FileChooser.ExtensionFilter("Alle Dateien", "*.*")
        );

        File baseFile = fileChooser.showSaveDialog(null);
        if (baseFile == null) {
            showStatus("Export abgebrochen.");
            return;
        }

        File dir = baseFile.getParentFile();
        String baseName = baseFile.getName();
        if (!baseName.toLowerCase().endsWith(".csv")) {
            baseName = baseName + ".csv";
        }
        baseName = baseName.substring(0, baseName.length() - 4);

        File patientsFile = new File(dir, baseName + "_patients.csv");
        File screeningsFile = new File(dir, baseName + "_screenings.csv");

        try {
            if (!dir.exists() && !dir.mkdirs()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Fehler beim Export");
                alert.setHeaderText("Verzeichnis kann nicht erstellt werden.");
                alert.setContentText("Bitte wählen Sie ein beschreibbares Verzeichnis.");
                alert.showAndWait();
                showStatus("Export fehlgeschlagen: Verzeichnis schreibgeschützt.");
                return;
            }

            for (Patient p : patientList) {
                if (!p.isValid()) {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Ungültige Daten");
                    alert.setHeaderText("Export abgebrochen");
                    alert.setContentText("Patient '" + p.getPatientName() + "' ist nicht plausibel.");
                    alert.showAndWait();
                    showStatus("Export abgebrochen: Ungültige Patientendaten bei " + p.getPatientName());
                    return;
                }
            }
            for (ScreeningData s : screeningList) {
                if (!Validator.isValidScreening(s)) {
                    List<String> errors = Validator.getValidationErrors(s);
                    StringBuilder msg = new StringBuilder("Screening für '");
                    msg.append(s.getPatientName()).append("' ist nicht plausibel:\n");
                    for (String e : errors) {
                        msg.append("- ").append(e).append("\n");
                    }
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Ungültige Daten");
                    alert.setHeaderText("Export abgebrochen");
                    alert.setContentText(msg.toString());
                    alert.showAndWait();
                    showStatus("Export abgebrochen: Ungültige Screenings.");
                    return;
                }
            }

            FileHandler.saveAllData(patientsFile.getAbsolutePath(), screeningsFile.getAbsolutePath());

            Alert success = new Alert(Alert.AlertType.INFORMATION);
            success.setTitle("Export erfolgreich");
            success.setHeaderText("Daten erfolgreich exportiert");
            success.setContentText("Die CSV-Dateien wurden gespeichert:\n"
                    + patientsFile.getAbsolutePath() + "\n"
                    + screeningsFile.getAbsolutePath());
            success.showAndWait();

            showStatus("Export erfolgreich: " + patientsFile.getName() + ", " + screeningsFile.getName());

        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Exportfehler");
            alert.setHeaderText("Fehler beim Speichern der CSV-Dateien.");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
            showStatus("Export fehlgeschlagen: " + e.getMessage());
        }
    }

    private void importData() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Import: CSV-Dateien wählen");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("CSV-Dateien", "*.csv"),
                new FileChooser.ExtensionFilter("Alle Dateien", "*.*")
        );

        File patientsFile = fileChooser.showOpenDialog(null);
        if (patientsFile == null) {
            showStatus("Import abgebrochen.");
            return;
        }

        fileChooser.setInitialDirectory(patientsFile.getParentFile());
        File screeningsFile = fileChooser.showOpenDialog(null);
        if (screeningsFile == null) {
            showStatus("Import abgebrochen – keine Screening-Datei ausgewählt.");
            return;
        }

        try {
            FileHandler.loadAllData(
                    patientsFile.getAbsolutePath(),
                    screeningsFile.getAbsolutePath()
            );

            patientList.setAll(DataStorage.getPatients());
            screeningList.setAll(DataStorage.getScreenings());

            int importedPatients = patientList.size();
            int importedScreenings = screeningList.size();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Import erfolgreich");
            alert.setHeaderText(null);
            alert.setContentText("Importiert:\n"
                    + importedPatients + " Patienten\n"
                    + importedScreenings + " Screenings");
            alert.showAndWait();

            showStatus("Import erfolgreich: " + patientsFile.getName() + ", " + screeningsFile.getName());

            txtFilterName.setText("");

            patientTable.getSortOrder().clear();
            screeningTable.getSortOrder().clear();

        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Importfehler");
            alert.setHeaderText("Fehler beim Laden der CSV-Dateien.");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
            showStatus("Import fehlgeschlagen: " + e.getMessage());
        }
    }

    private void clearPatientForm() {
        txtName.clear();
        txtVorname.clear();
        dpGeburtsdatum.setValue(null);
        txtVersichertenId.clear();
        txtStrasse.clear();
        txtHausnummer.clear();
        txtPlz.clear();
        txtOrt.clear();
        cbVersichertenArt.getSelectionModel().selectFirst();
        cbGeschlecht.getSelectionModel().selectFirst();
        txtBetriebsstaettenNr.clear();
        txtArztnummer.clear();
        txtArztname.clear();
    }

    private void clearScreeningForm() {
        dpUntersuchungsdatum.setValue(null);
        chkVerdachtsdiagnoseND.setSelected(false);
        chkMalignesMelanom.setSelected(false);
        chkBasalzellkarzinom.setSelected(false);
        chkSpinozellulaeresKarzinom.setSelected(false);
        chkAndererHautkrebs.setSelected(false);
        chkSonstigerDermatologischerBefund.setSelected(false);
        chkUeberweisungAnDermatologen.setSelected(false);
        chkGesundheitsuntersuchung.setSelected(false);
        chkUeberweisungImRahmenHKS.setSelected(false);
        chkUeberweisenderArztHatHKS.setSelected(false);
        chkAngabeUeberweisenderArzt.setSelected(false);
        chkUeberweisenderArztMalignesMelanom.setSelected(false);
        chkUeberweisenderArztBasalzellkarzinom.setSelected(false);
        chkUeberweisenderArztSpinozellulaeresKarzinom.setSelected(false);
        chkUeberweisenderArztAndererHautkrebs.setSelected(false);
        chkDermatologeVerdachtsdiagnose.setSelected(false);
        chkDermatologeMalignesMelanom.setSelected(false);
        chkDermatologeBasalzellkarzinom.setSelected(false);
        chkDermatologeSpinozellulaeresKarzinom.setSelected(false);
        chkDermatologeAndererHautkrebs.setSelected(false);
        chkDermatologeSonstigerBiopsieBefund.setSelected(false);
        chkBiopsieOderExzision.setSelected(false);
        txtAnzahlBiopsien.clear();
        chkAnderweitigeTherapie.setSelected(false);
        chkKeineWeitereTherapie.setSelected(false);
        chkHistopathologieMalignesMelanom.setSelected(false);
        cbMelanomKlassifikation.getSelectionModel().clearSelection();
        cbMelanomBreslow.getSelectionModel().clearSelection();
        chkHistopathologieBasalzellkarzinom.setSelected(false);
        txtBasalzellHorizontal.clear();
        txtBasalzellVertikal.clear();
        chkHistopathologieSpinozellulaeresKarzinom.setSelected(false);
        cbSpinozellulaeresKlassifikation.getSelectionModel().clearSelection();
        cbSpinozellulaeresGrading.getSelectionModel().clearSelection();
        chkHistopathologieAndererHautkrebs.setSelected(false);
        chkHistopathologieAtypischerNaevus.setSelected(false);
        chkHistopathologieJunktionalerNaevus.setSelected(false);
        chkHistopathologieAktinischeKeratose.setSelected(false);
        chkHistopathologieAndereVeränderung.setSelected(false);
    }

    private void showStatus(String msg) {
        statusLabel.setText(msg);
    }

    private HBox createRow(String label, Node control) {
        HBox row = new HBox(10);
        row.setPrefWidth(600);

        Label lbl = new Label(label + ":");
        lbl.setPrefWidth(180);
        lbl.setMinWidth(180);
        lbl.setMaxWidth(180);

        if (control instanceof Control c) {
            c.setPrefWidth(280);
        }

        row.getChildren().addAll(lbl, control);
        return row;
    }

    private VBox dataTabContent;

    private final Button btnDeleteSelectedPatient = new Button("✅ Patient löschen");
    private final Button btnDeleteSelectedScreening = new Button("✅ Screening löschen");

    public javafx.scene.Node getView() {
        TabPane tabPane = new TabPane();
        tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);

        Tab tabPatient = new Tab("Patienten");
        tabPatient.setContent(new VBox(10, setupPatientForm(), patientTable));

        Tab tabScreening = new Tab("Untersuchungen");
        tabScreening.setContent(new VBox(10, setupScreeningForm(), screeningTable));

        Tab tabData = new Tab("Daten & Export");
        tabData.setContent(dataTabContent);

        tabPane.getTabs().addAll(tabPatient, tabScreening, tabData);

        return new VBox(10, tabPane, statusLabel);
    }

    private void updateDeleteButtonsState() {
        btnDeleteSelectedPatient.setDisable(patientTable.getSelectionModel().getSelectedItem() == null);
        btnDeleteSelectedScreening.setDisable(screeningTable.getSelectionModel().getSelectedItem() == null);
    }

    private void deleteSelectedPatient() {
        Patient selected = patientTable.getSelectionModel().getSelectedItem();
        if (selected == null) {
            showStatus("❌ Kein Patient ausgewählt.");
            return;
        }

        Alert confirm = new Alert(Alert.AlertType.CONFIRMATION);
        confirm.setTitle("Patient löschen");
        confirm.setHeaderText("Patient löschen: " + selected.getPatientName());
        confirm.setContentText(
            "Achtung:\n"
            + "• Alle Screenings dieses Patienten werden ebenfalls gelöscht.\n"
            + "• Dieser Vorgang kann nicht rückgängig gemacht werden.\n\n"
            + "Möchten Sie den Patienten wirklich löschen?"
        );
        confirm.getButtonTypes().setAll(ButtonType.YES, ButtonType.NO);

        confirm.showAndWait().ifPresent(response -> {
            if (response == ButtonType.YES) {
                DataStorage.getScreenings().removeIf(s -> selected.equals(s.getPatient()));
                DataStorage.getPatients().remove(selected);
                patientList.setAll(DataStorage.getPatients());
                screeningList.setAll(DataStorage.getScreenings());

                if (currentPatient != null && currentPatient.equals(selected)) {
                    currentPatient = null;
                    clearPatientForm();
                }

                showStatus("✅ Patient '" + selected.getPatientName() + "' und alle zugehörigen Screenings gelöscht.");
                updateDeleteButtonsState();
            }
        });
    }

    private void deleteSelectedScreening() {
        ScreeningData selected = screeningTable.getSelectionModel().getSelectedItem();
        if (selected == null) {
            showStatus("❌ Kein Screening ausgewählt.");
            return;
        }

        Alert confirm = new Alert(Alert.AlertType.CONFIRMATION);
        confirm.setTitle("Screening löschen");
        confirm.setHeaderText("Screening löschen für: " + selected.getPatientName());
        confirm.setContentText(
                "Achtung:\n"
                + "• Dieses Screening wird unwiderruflich gelöscht.\n"
                + "• Der Patient bleibt erhalten.\n\n"
                + "Möchten Sie das Screening wirklich löschen?"
        );
        confirm.getButtonTypes().setAll(ButtonType.YES, ButtonType.NO);

        confirm.showAndWait().ifPresent(response -> {
            if (response == ButtonType.YES) {
                DataStorage.getScreenings().remove(selected);
                screeningList.setAll(DataStorage.getScreenings());
                showStatus("✅ Screening für '" + selected.getPatientName() + "' gelöscht.");
                updateDeleteButtonsState();
            }
        });
    }

    private void generateReport() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    private void editSelectedPatient() {
        Patient selected = patientTable.getSelectionModel().getSelectedItem();
        if (selected == null) {
            return;
        }

        currentPatient = selected;

        txtName.setText(selected.getName());
        txtVorname.setText(selected.getVorname());
        dpGeburtsdatum.setValue(selected.getGeburtsdatum());
        txtVersichertenId.setText(selected.getVersichertenId());
        txtStrasse.setText(selected.getStrasse());
        txtHausnummer.setText(selected.getHausnummer());
        txtPlz.setText(selected.getPlz());
        txtOrt.setText(selected.getOrt());
        cbVersichertenArt.getSelectionModel().select(selected.getVersichertenArt());
        cbGeschlecht.getSelectionModel().select(selected.getGeschlecht());
        txtBetriebsstaettenNr.setText(selected.getBetriebsstaettenNr());
        txtArztnummer.setText(selected.getArztnummer());
        txtArztname.setText(selected.getArztname());

        isEditingPatient = true;
        isEditingScreening = false;

        showStatus("Bearbeite Patient: " + selected.getPatientName());
    }

    private void editSelectedScreening() {
        ScreeningData selected = screeningTable.getSelectionModel().getSelectedItem();
        if (selected == null) {
            return;
        }

        currentPatient = selected.getPatient();

        dpUntersuchungsdatum.setValue(selected.getUntersuchungsdatum());

        chkVerdachtsdiagnoseND.setSelected(selected.isVerdachtsdiagnoseND());
        chkMalignesMelanom.setSelected(selected.isMalignesMelanom());
        chkBasalzellkarzinom.setSelected(selected.isBasalzellkarzinom());
        chkSpinozellulaeresKarzinom.setSelected(selected.isSpinozellulaeresKarzinom());
        chkAndererHautkrebs.setSelected(selected.isAndererHautkrebs());
        chkSonstigerDermatologischerBefund.setSelected(selected.isSonstigerDermatologischerBefund());
        chkUeberweisungAnDermatologen.setSelected(selected.isUeberweisungAnDermatologen());

        chkGesundheitsuntersuchung.setSelected(selected.isGesundheitsuntersuchung());

        chkUeberweisungImRahmenHKS.setSelected(selected.isUeberweisungImRahmenHKS());
        chkUeberweisenderArztHatHKS.setSelected(selected.isUeberweisenderArztHatHKS());

        chkAngabeUeberweisenderArzt.setSelected(selected.isAngabeUeberweisenderArzt());
        chkUeberweisenderArztMalignesMelanom.setSelected(selected.isUeberweisenderArztMalignesMelanom());
        chkUeberweisenderArztBasalzellkarzinom.setSelected(selected.isUeberweisenderArztBasalzellkarzinom());
        chkUeberweisenderArztSpinozellulaeresKarzinom.setSelected(selected.isUeberweisenderArztSpinozellulaeresKarzinom());
        chkUeberweisenderArztAndererHautkrebs.setSelected(selected.isUeberweisenderArztAndererHautkrebs());

        chkDermatologeVerdachtsdiagnose.setSelected(selected.isDermatologeVerdachtsdiagnose());
        chkDermatologeMalignesMelanom.setSelected(selected.isDermatologeMalignesMelanom());
        chkDermatologeBasalzellkarzinom.setSelected(selected.isDermatologeBasalzellkarzinom());
        chkDermatologeSpinozellulaeresKarzinom.setSelected(selected.isDermatologeSpinozellulaeresKarzinom());
        chkDermatologeAndererHautkrebs.setSelected(selected.isDermatologeAndererHautkrebs());
        chkDermatologeSonstigerBiopsieBefund.setSelected(selected.isDermatologeSonstigerBiopsieBefund());

        chkBiopsieOderExzision.setSelected(selected.isBiopsieOderExzision());
        txtAnzahlBiopsien.setText(selected.getAnzahlBiopsien() == 0 ? "" : String.valueOf(selected.getAnzahlBiopsien()));
        chkAnderweitigeTherapie.setSelected(selected.isAnderweitigeTherapie());
        chkKeineWeitereTherapie.setSelected(selected.isKeineWeitereTherapie());

        chkHistopathologieMalignesMelanom.setSelected(selected.isHistopathologieMalignesMelanom());
        cbMelanomKlassifikation.getSelectionModel().select(selected.getMelanomKlassifikation());
        cbMelanomBreslow.getSelectionModel().select(selected.getMelanomBreslow());

        chkHistopathologieBasalzellkarzinom.setSelected(selected.isHistopathologieBasalzellkarzinom());
        txtBasalzellHorizontal.setText(selected.getBasalzellHorizontal() == null ? "" : String.valueOf(selected.getBasalzellHorizontal()));
        txtBasalzellVertikal.setText(selected.getBasalzellVertikal() == null ? "" : String.valueOf(selected.getBasalzellVertikal()));

        chkHistopathologieSpinozellulaeresKarzinom.setSelected(selected.isHistopathologieSpinozellulaeresKarzinom());
        cbSpinozellulaeresKlassifikation.getSelectionModel().select(selected.getSpinozellulaeresKlassifikation());
        cbSpinozellulaeresGrading.getSelectionModel().select(selected.getSpinozellulaeresGrading());

        chkHistopathologieAndererHautkrebs.setSelected(selected.isHistopathologieAndererHautkrebs());
        chkHistopathologieAtypischerNaevus.setSelected(selected.isHistopathologieAtypischerNaevus());
        chkHistopathologieJunktionalerNaevus.setSelected(selected.isHistopathologieJunktionalerNaevus());
        chkHistopathologieAktinischeKeratose.setSelected(selected.isHistopathologieAktinischeKeratose());
        chkHistopathologieAndereVeränderung.setSelected(selected.isHistopathologieAndereVeränderung());

        isEditingScreening = true;
        isEditingPatient = false;

        showStatus("Bearbeite Screening für: " + selected.getPatientName());
    }
}