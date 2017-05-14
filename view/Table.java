package view;

import java.util.Objects;

import controller.ProsesBarang;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * Created by Alfatih on 20/04/2016.
 * This is the View of MVC
 * Use to create most View that show in the Scene like Label, Text Field, Button etc
 */
public class Table{

	public Table(){

	}

	public static class BarangBeli{

		private final SimpleStringProperty KodeSewa;
		private final SimpleStringProperty NomorPlat;
		private final SimpleStringProperty JenisMobil;
		private final SimpleStringProperty NamaPenyewa;
		private final SimpleStringProperty LamaSewa;

		private BarangBeli(String KS, String NP, String JM, String NPE, String LS){
			this.KodeSewa = new SimpleStringProperty(KS);
			this.NomorPlat = new SimpleStringProperty(NP);
			this.JenisMobil = new SimpleStringProperty(JM);
			this.NamaPenyewa = new SimpleStringProperty(NPE);
			this.LamaSewa = new SimpleStringProperty(LS);
		}

		public String getKodeSewa(){
			return KodeSewa.get();
		}

		public void setKodeSewa(String KS){
			KodeSewa.set(KS);
		}

		public String getNomorPlat(){
			return NomorPlat.get();
		}

		public void setNomorPlat(String NP){
			NomorPlat.set(NP);
		}

		public String getJenisMobil(){
			return JenisMobil.get();
		}

		public void setJenisMobil(String JM){
			JenisMobil.set(JM);
		}

		public String getNamaPenyewa(){
			return NamaPenyewa.get();
		}

		public void setNamaPenyewa(String NPE){
			NamaPenyewa.set(NPE);
		}

		public String getLamaSewa(){
			return LamaSewa.get();
		}

		public void setLamaSewa(String LS){
			LamaSewa.set(LS);
		}

	}

	private TableView<BarangBeli> table = new TableView<>();
	private final ObservableList<BarangBeli> data2 =  FXCollections.observableArrayList();
	private HBox hb = new HBox();
	private int no = 1;
	private int jumlah,i;
	private String[] data = new String[4];

	public void setView(Stage stage){

		ProsesBarang pbrg = new ProsesBarang();
		Scene scene = new Scene(new Group());

		stage.setTitle("Hire Car Table");
		stage.setResizable(false);
		stage.setWidth(785);
		stage.setHeight(485);

		final Alert errHandle = new Alert(Alert.AlertType.INFORMATION);
		errHandle.setTitle("Perhatian");
		errHandle.setHeaderText(null);
		errHandle.setContentText("Isian tidak lengkap !!");

		final Alert sqlException = new Alert(Alert.AlertType.INFORMATION);
		sqlException.setTitle("Perhatian");
		sqlException.setHeaderText(null);
		sqlException.setContentText("Pilih Kode Sewa !!");

		TableColumn Kolom_KodeSewa = new TableColumn("Kode Sewa");
		Kolom_KodeSewa.setPrefWidth(120);
		Kolom_KodeSewa.setMaxWidth(120);
		Kolom_KodeSewa.setCellValueFactory(
				new PropertyValueFactory<BarangBeli, String>("KodeSewa")
		);
		TableColumn Kolom_JenisMobil = new TableColumn("Jenis Mobil");
		Kolom_JenisMobil.setPrefWidth(120);
		Kolom_JenisMobil.setMaxWidth(120);
		Kolom_JenisMobil.setCellValueFactory(
			new PropertyValueFactory<BarangBeli,String>("JenisMobil")
			);
		TableColumn Kolom_NomorPlat = new TableColumn("Nomor Plat");
		Kolom_NomorPlat.setPrefWidth(120);
		Kolom_NomorPlat.setMaxWidth(120);
		Kolom_NomorPlat.setCellValueFactory(
			new PropertyValueFactory<BarangBeli,String>("NomorPlat")
			);

		TableColumn Kolom_NamaPenyewa = new TableColumn("Nama Penyewa");
		Kolom_NamaPenyewa.setPrefWidth(120);
		Kolom_NamaPenyewa.setMaxWidth(120);
		Kolom_NamaPenyewa.setCellValueFactory(
			new PropertyValueFactory<BarangBeli,String>("NamaPenyewa")
			);

		TableColumn Kolom_LamaSewa = new TableColumn("Lama Sewa");
		Kolom_LamaSewa.setPrefWidth(120);
		Kolom_LamaSewa.setMaxWidth(120);
		Kolom_LamaSewa.setCellValueFactory(
				new PropertyValueFactory<BarangBeli, String>("LamaSewa")
		);

		table.getColumns().addAll(Kolom_KodeSewa,Kolom_JenisMobil,Kolom_NomorPlat,Kolom_NamaPenyewa,Kolom_LamaSewa);
		table.setItems(data2);

		final VBox vbox = new VBox();
		vbox.setSpacing(5);
		vbox.setLayoutY(0);
		vbox.getChildren().addAll(table,hb);
		vbox.setPadding(new Insets(10,0,0,10));

		jumlah = 0;
		String[][] daftar = new String[25][10];

		try{
			pbrg.prosesBrg();
			jumlah = pbrg.getJml();
			daftar = pbrg.getHasil();
		}catch(Exception e){
			System.out.println(pbrg.getError());
		}

		for(i=0;i<jumlah;i++){
			data2.add(new BarangBeli(daftar[i][0],daftar[i][1],daftar[i][2],daftar[i][3],daftar[i][4]));
			no++;
		}

		final TextField addJenisMobil = new TextField();
		addJenisMobil.setPromptText("Jenis Mobil");
		addJenisMobil.setText("");
		addJenisMobil.setMaxWidth(Kolom_JenisMobil.getPrefWidth());

		final TextField addNomorPlat = new TextField();
		addNomorPlat.setPromptText("Nomor Plat");
		addNomorPlat.setText("");
		addNomorPlat.setMaxWidth(Kolom_NomorPlat.getPrefWidth());

		final TextField addNamaPenyewa = new TextField();
		addNamaPenyewa.setPromptText("Nama Penyewa");
		addNamaPenyewa.setText("");
		addNamaPenyewa.setMaxWidth(Kolom_NamaPenyewa.getPrefWidth());

		final TextField addLamaSewa = new TextField();
		addLamaSewa.setPromptText("Lama Sewa");
		addLamaSewa.setText("");
		addLamaSewa.setMaxWidth(Kolom_LamaSewa.getPrefWidth());

		final Button addButton = new Button("Tambah");
		addButton.setOnAction(e -> {
			if (Objects.equals(addNomorPlat.getText(), "") || Objects.equals(addJenisMobil.getText(), "") || Objects.equals(addNamaPenyewa.getText(), "") || Objects.equals(addLamaSewa.getText(), "")) {
				errHandle.showAndWait();
			} else {
				data2.add(new BarangBeli("A" + no, addNomorPlat.getText(),
						addJenisMobil.getText(), addNamaPenyewa.getText(), addLamaSewa.getText()));
				pbrg.tambahBarang("A" + no, addNomorPlat.getText(), addJenisMobil.getText(),
						addNamaPenyewa.getText(), addLamaSewa.getText());
				addJenisMobil.setText("");
				addNomorPlat.setText("");
				addNamaPenyewa.setText("");
				addLamaSewa.setText("");
				no++;
			}
		});

		final Button updateButton = new Button("Update");
		updateButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {

				Group rootUpdate = new Group();
				Scene updateScene = new Scene(rootUpdate, 240, 450);

				final TextField updateNomorPlat = new TextField();
				updateNomorPlat.setPromptText("Nomor Plat");
				updateNomorPlat.setMaxWidth(200);
				updateNomorPlat.setLayoutX(50);
				updateNomorPlat.setLayoutY(150);

				final TextField updateJenisMobil = new TextField();
				updateJenisMobil.setPromptText("Jenis Mobil");
				updateJenisMobil.setMaxWidth(200);
				updateJenisMobil.setLayoutX(50);
				updateJenisMobil.setLayoutY(200);

				final TextField updateNamaPenyewa = new TextField();
				updateNamaPenyewa.setPromptText("Nama Penyewa");
				updateNamaPenyewa.setMaxWidth(200);
				updateNamaPenyewa.setLayoutX(50);
				updateNamaPenyewa.setLayoutY(250);

				final TextField updateLamaSewa = new TextField();
				updateLamaSewa.setPromptText("Lama Sewa");
				updateLamaSewa.setMaxWidth(200);
				updateLamaSewa.setLayoutX(50);
				updateLamaSewa.setLayoutY(300);

				updateNomorPlat.setDisable(true);
				updateJenisMobil.setDisable(true);
				updateNamaPenyewa.setDisable(true);
				updateLamaSewa.setDisable(true);

				final Label setID = new Label("Pilih\nKode Sewa Terlebih Dahulu :");
				setID.setLayoutX(50);
				setID.setLayoutY(50);

				final ComboBox<String> updateID = new ComboBox<>();
				jumlah = 0;
				String[][] daftar = new String[25][10];
				try {
					pbrg.prosesBrg();
					jumlah = pbrg.getJml();
					daftar = pbrg.getHasil();
				} catch (Exception e) {
					System.out.println(pbrg.getError());
				}
				for (int i = 0; i < jumlah; i++) {
					updateID.getItems().add(daftar[i][0]);
				}
				updateID.setValue(null);
				updateID.setPrefWidth(150);
				updateID.setMaxWidth(200);
				updateID.setLayoutX(50);
				updateID.setLayoutY(100);
				updateID.setPromptText("Kode Sewa");
				updateID.valueProperty().addListener(new ChangeListener<String>() {
					@Override
					public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
						data = pbrg.getByKode(newValue);
						updateNomorPlat.setText(data[0]);
						updateJenisMobil.setText(data[1]);
						updateNamaPenyewa.setText(data[2]);
						updateLamaSewa.setText(data[3]);
						updateNomorPlat.setDisable(false);
						updateJenisMobil.setDisable(false);
						updateNamaPenyewa.setDisable(false);
						updateLamaSewa.setDisable(false);
					}
				});

				final Button sureUpdate = new Button("Update");
				sureUpdate.setLayoutX(50);
				sureUpdate.setLayoutY(350);

				final Button cancelUpdate = new Button("Cancel");
				cancelUpdate.setLayoutX(150);
				cancelUpdate.setLayoutY(350);

				rootUpdate.getChildren().addAll(cancelUpdate, updateJenisMobil, updateLamaSewa, updateNamaPenyewa, updateNomorPlat, setID, updateID, sureUpdate);
				Stage popUpUpdate = new Stage();
				popUpUpdate.setTitle("Update");
				popUpUpdate.setResizable(false);
				popUpUpdate.initModality(Modality.APPLICATION_MODAL);
				popUpUpdate.setScene(updateScene);
				popUpUpdate.show();

				cancelUpdate.setOnAction(event1 -> popUpUpdate.close());

				sureUpdate.setOnAction(event1 -> {
					if (Objects.equals(updateID.getValue(), "") || Objects.equals(updateNomorPlat.getText(), "") || Objects.equals(updateNamaPenyewa.getText(), "") || Objects.equals(updateLamaSewa.getText(), "")) {
						errHandle.showAndWait();
					} else {
						if (Objects.equals(updateNomorPlat.getText(), data[0]) && Objects.equals(updateJenisMobil.getText(), data[1]) && Objects.equals(updateNamaPenyewa.getText(), data[2]) && Objects.equals(updateLamaSewa.getText(), data[3])) {
							System.out.println("Data tidak mengalami perubahan");
						} else {
							pbrg.updateBarang(updateID.getValue(), updateNomorPlat.getText(), updateJenisMobil.getText(), updateNamaPenyewa.getText(), updateLamaSewa.getText());
							table.getItems().clear();
							no = 1;
							jumlah = 0;
							String[][] daftar1 = new String[25][10];

							try {
								pbrg.prosesBrg();
								jumlah = pbrg.getJml();
								daftar1 = pbrg.getHasil();
							} catch (Exception e) {
								System.out.println(pbrg.getError());
							}

							for (i = 0; i < jumlah; i++) {
								data2.add(new BarangBeli(daftar1[i][0], daftar1[i][1], daftar1[i][2], daftar1[i][3], daftar1[i][4]));
								no++;
							}
						}
						popUpUpdate.close();
					}
				});

			}
		});
		final Button deleteButton = new Button("Delete");
		deleteButton.setOnAction(event -> {
			Group rootDelete = new Group();
			Scene deleteScene = new Scene(rootDelete, 240, 150);

			final ComboBox<String> deleteID = new ComboBox<>();
			jumlah = 0;
			String[][] daftar1 = new String[25][10];
			try {
				pbrg.prosesBrg();
				jumlah = pbrg.getJml();
				daftar1 = pbrg.getHasil();
			} catch (Exception e) {
				System.out.println(pbrg.getError());
			}
			for (int i1 = 0; i1 < jumlah; i1++) {
				deleteID.getItems().add(daftar1[i1][0]);
			}
			deleteID.setPromptText("Kode Sewa");
			deleteID.setValue(null);
			deleteID.setPrefWidth(150);
			deleteID.setMaxWidth(200);
			deleteID.setLayoutX(50);
			deleteID.setLayoutY(50);

			final Button sureDelete = new Button("Delete");
			sureDelete.setLayoutX(50);
			sureDelete.setLayoutY(100);

			final Button cancelDelete = new Button("Cancel");
			cancelDelete.setLayoutX(150);
			cancelDelete.setLayoutY(100);

			rootDelete.getChildren().addAll(sureDelete, deleteID, cancelDelete);
			Stage popUpDelete = new Stage();
			popUpDelete.setTitle("Delete");
			popUpDelete.setResizable(false);
			popUpDelete.initModality(Modality.APPLICATION_MODAL);
			popUpDelete.setScene(deleteScene);
			popUpDelete.show();

			cancelDelete.setOnAction(event1 -> {
				popUpDelete.close();
			});

			sureDelete.setOnAction(new EventHandler<ActionEvent>() {
				   @Override
				   public void handle(ActionEvent event1) {
					   if (Objects.equals(deleteID.getValue(), "")) {
						   errHandle.showAndWait();
					   } else {
						   String checkData = "";
						   try {
							   pbrg.getByKode(deleteID.getValue());
							   checkData = pbrg.getKode();
							   System.out.println(checkData);
						   } catch (Exception e) {
							   System.out.println(pbrg.getError());
						   }
						   if (Objects.equals(checkData, null)) {
							   sqlException.showAndWait();
						   } else {
							   pbrg.deleteBarang(deleteID.getValue());
							   table.getItems().clear();
							   no = 1;
							   jumlah = 0;
							   String[][] daftar11 = new String[25][10];

							   try {
								   pbrg.prosesBrg();
								   jumlah = pbrg.getJml();
								   daftar11 = pbrg.getHasil();
							   } catch (Exception e) {
								   System.out.println(pbrg.getError());
							   }

							   for (i = 0; i < jumlah; i++) {
								   daftar11[i][0] = "A" + no;
								   data2.add(new BarangBeli(daftar11[i][0], daftar11[i][1], daftar11[i][2], daftar11[i][3], daftar11[i][4]));
								   no++;
							   }
							   pbrg.deleteAll();
							   for (i = 0; i < jumlah; i++) {
								   pbrg.tambahBarang(daftar11[i][0], daftar11[i][1], daftar11[i][2], daftar11[i][3], daftar11[i][4]);
							   }
							   popUpDelete.close();
						   }
					   }
				   }
			   }
			);
		});

		final Button clearDataButton = new Button("Clear Data");
		clearDataButton.setOnAction(event -> {
            table.getItems().clear();
            pbrg.deleteAll();
            no = 1;
        });
			hb.getChildren().

					addAll(addJenisMobil, addNomorPlat, addNamaPenyewa, addLamaSewa, addButton, updateButton, deleteButton, clearDataButton);

		hb.setSpacing(6);
			((Group)scene.getRoot()).

			getChildren()

					.

			addAll(vbox);

		stage.setScene(scene);
			stage.show();

		}

	}