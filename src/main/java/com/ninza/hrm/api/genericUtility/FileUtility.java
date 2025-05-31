package com.ninza.hrm.api.genericUtility;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class FileUtility 
{
public String getDataFromProperties(String key) throws IOException
{
	FileInputStream fis=new FileInputStream("./config_env_data/configEnvData.properties");
	Properties pobj=new Properties();
	pobj.load(fis);
	String data = pobj.getProperty(key);
	return data;
}
}