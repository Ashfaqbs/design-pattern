package design_patterns_java.structural.composite;

public class Client {
	public static void main(String[] args) {
		// Create files
		File file1 = new File("Document1.txt");
		File file2 = new File("Picture1.png");
		File file3 = new File("Video1.mp4");

		// Create a folder and add files to it
		Folder folder1 = new Folder("My Documents");
		folder1.addComponent(file1);
		folder1.addComponent(file2);

		// Create another folder
		Folder folder2 = new Folder("My Media");
		folder2.addComponent(file3);

		// Add folder2 to folder1
		folder1.addComponent(folder2);

		// Show details of folder1
		folder1.showDetails();
	}
}
