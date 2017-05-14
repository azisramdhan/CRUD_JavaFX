package model;

/**
 * Created by Alfatih on 20/04/2016.
 * This class is use as Model of MVC
 * Use to Execute some query (like select and update)
 */
public class Barang extends DB{

	/**
	 * Constructor
	 */
	public Barang() throws Exception{
		super();
	}

	/**
	 * This method is use to execute "select" query that get data from database using driver manager
	 */
	public void getDaftar_Barang(){
		try{
			String query = "Select kode_sewa,nomor_plat,jenis_mobil,nama_penyewa,lama_sewa from `sewa` order by id";
			createQuery(query);
		}catch(Exception e){
			System.out.println(e.toString());
		}
	}

	/**
	 * This method is use to execute "update" query that update data at database using driver manager
	 */
	public void updateById(String kode_sewa, String nomor_plat, String jenis_mobil, String nama_penyewa, String lama_sewa){
		try{
			String query = "update `sewa` set nomor_plat='"+nomor_plat+"', jenis_mobil='"+jenis_mobil+"',nama_penyewa='"+nama_penyewa+"',lama_sewa='"+lama_sewa+"' where kode_sewa='"+kode_sewa+"'";
			System.out.println(query);
			createUpdate(query);
		}catch(Exception e){
			System.out.println(e.toString());
		}
	}

	/**
	 * This method is use to execute "delete" query that delete data at database using driver manager
	 */
	public void deleteById(String kode_sewa){
		try{
			String query = "delete from `sewa` where kode_sewa='" + kode_sewa +"'";
			System.out.println(query);
			createUpdate(query);
		}catch(Exception e){
			System.out.println(e.toString());
		}
	}

	/**
	 * This method is use to execute "delete" query that delete all data at database using driver manager
	 */
	public void deleteAll(){
		try{
			String query = "delete from `sewa`";
			System.out.println(query);
			createUpdate(query);
		}catch(Exception e){
			System.out.println(e.toString());
		}
	}

	/**
	 * This method is use to execute "insert" query that insert data into database using driver manager
	 */
	public void insertBarang(String kode_sewa, String nomor_plat, String jenis_mobil, String nama_penyewa, String lama_sewa){
		try{
			String query="Insert into `sewa` (kode_sewa,nomor_plat,jenis_mobil,nama_penyewa,lama_sewa)VALUES('"+kode_sewa+"','"+nomor_plat+"','"+jenis_mobil+"','"+nama_penyewa+"','"+lama_sewa+"')";
			System.out.println(query);
			createUpdate(query);
		}catch(Exception e){
			System.out.println(e.toString());
		}
	}
}