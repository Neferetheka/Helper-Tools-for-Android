public abstract class HTMLHelper
{
	/*
	* Permit to convert html text to plain text
	*/
	public static String convertFromHTML(String chaine, boolean isSpecial)
	{
		return chaine.replace("&eacute;", "é").replace("&egrave;", "è").replace("&agrave;", "à")
				.replace("&ecirc;", "ê").replace("&ocirc;", "ô")
				.replace("&ucirc;", "û").replace("&ugrave;", "ù").replace("&ccedil;", "ç").replace("&acirc;", "â");
	}
}
