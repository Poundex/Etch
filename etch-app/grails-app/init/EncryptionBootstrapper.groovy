import java.nio.file.Files
import java.nio.file.attribute.PosixFilePermission

/**
 * Created by poundex on 11/12/15.
 */
class EncryptionBootstrapper
{
	private EncryptionBootstrapper() { }

	public static String getEncryptionPassword()
	{
		return passwordFile.text
	}

	private static File getPasswordFile()
	{
		File file = new File(etchHome, "password")
		if( ! file.isFile())
			createPasswordFile(file)

		return file
	}

	private static File getEtchHome()
	{
		File file = new File("${System.properties['user.home']}/.etch")
		if( ! file.isDirectory())
			file.mkdir()

		return file
	}

	private static String generatePassword()
	{
		def pw = UUID.randomUUID()
		5.times {
			pw = pw.toString().bytes.encodeBase64()
		}
		return pw
	}

	private static void createPasswordFile(File file)
	{
		file.text = generatePassword()
		Files.setPosixFilePermissions(file.toPath(), [PosixFilePermission.OWNER_READ].toSet())
	}
}
