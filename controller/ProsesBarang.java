package controller;

import model.Barang;

/**
 * Created by Alfatih on 20/04/2016.
 * This class is use as Model of MVC
 * Use to Put data (such as Kode Sewa, Nomor Plat, Jenis Mobil etc) from database and save to global variable
 */

public class ProsesBarang{

	private int jml;
	private String[][] daftar = new String[25][10];
	private String kode;

	private String error;

	public ProsesBarang(){
		this.jml = 0;
	}

	/**
	 * This method is use get data from database
	 * Save data into Global Variable
	 */
	public void prosesBrg(){
		jml = 0;
		try{
			Barang brg =  new Barang();
			brg.getDaftar_Barang();

			while(brg.getResult().next()){

				String kode_sewa = brg.getResult().getString(1);
				String nomor_plat = brg.getResult().getString(2);
				String jenis_mobil = brg.getResult().getString(3);
				String nama_penyewa = brg.getResult().getString(4);
				String lama_sewa = brg.getResult().getString(5);

				daftar[jml][0]= kode_sewa;
				daftar[jml][1]= nomor_plat;
				daftar[jml][2]= jenis_mobil;
				daftar[jml][3]= nama_penyewa;
				daftar[jml][4]= lama_sewa;
				jml++;
			}
			brg.closeResult();
			brg.closeConnection();
		}catch(Exception e){
			error = e.toString();
		}

	}

	/**
	 * This method is use to return number of data that get from database
	 */
	public int getJml(){
			return this.jml;
		}

	/**
	 * This method is use to return all data that get from database
	 */
	public String[][] getHasil() {
		return this.daftar;
	}

	/**
	 * This method is use to return message error
	 */
	public String getError(){
		return this.error;
	}

	/**
	 * This method is use to call function insert data and send some attribut as param
	 */
	public void tambahBarang(String kode_sewa, String nomor_plat, String jenis_mobil, String nama_penyewa, String lama_sewa){
		try{
			Barang brg = new Barang();
			brg.insertBarang(kode_sewa, nomor_plat, jenis_mobil, nama_penyewa, lama_sewa);

			brg.closeResult();
			brg.closeConnection();
		} catch (Exception e) {
			error = e.toString();
		}
	}

	/**
	 * This method is use to call function update data and send some attribut as param
	 */
	public void updateBarang(String updateID,String updateNomorPlat,String updateJenisMobil, String updateNamaPenyewa,String updateLamaSewa){
		try{
			Barang brg = new Barang();
			brg.updateById(updateID, updateNomorPlat, updateJenisMobil, updateNamaPenyewa, updateLamaSewa);

			brg.closeResult();
			brg.closeConnection();
		}catch(Exception e){
			error = e.toString();
		}
	}

	/**
	 * This method is use to call function delete data and send attribut as param
	 */
	public void deleteBarang(String kode_sewa){
		try{
			Barang brg = new Barang();
			brg.deleteById(kode_sewa);

			brg.closeResult();
			brg.closeConnection();
		}catch(Exception e){
			error = e.toString();
		}
	}

	/**
	 * This method is use to call function delete All
	 */
	public void deleteAll(){
		try{
			Barang brg = new Barang();
			brg.deleteAll();

			brg.closeResult();
			brg.closeConnection();
		}catch(Exception e){
			error = e.toString();
		}
	}

	/**
	 * This method is use get record of data that have a current id (kode sewa)
	 */
	public String[] getByKode(String kode_sewa){

		String[] temp = new String[4];
		prosesBrg();
		kode = null;
		int i;
		for(i=0;i<jml;i++) {
			if (daftar[i][0].equals(kode_sewa)) {
				kode = kode_sewa;
				temp[0] = daftar[i][1];
				temp[1] = daftar[i][2];
				temp[2] = daftar[i][3];
				temp[3] = daftar[i][4];
			}
		}

		return  temp;
	}

	/**
	 * This method is use to return kode value if it isn't null
	 */
	public String getKode(){
		return this.kode;
	}

}