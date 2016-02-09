package model;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import org.apache.log4j.*;

public class TaskIO  {
    public static  SimpleDateFormat dateFormat = new SimpleDateFormat("'['yyyy-MM-dd HH:mm:ss.SSS']'");
    private static final Logger Log = Logger.getLogger(TaskIO.class);

    public static void write(TaskList tasks, OutputStream out) throws IOException {
        DataOutputStream newOut = new DataOutputStream(out);
        newOut.writeInt(tasks.size());
        for (Task task : tasks) {
            newOut.writeUTF(task.getTitle());
            newOut.writeInt(task.getTitle().length());
            newOut.writeBoolean(task.isActive());
            newOut.writeBoolean(task.isRepeated());
            if(task.isRepeated()) {
                newOut.writeLong(task.getRepeatInterval());
                newOut.writeLong(task.getStartTime().getTime());
                newOut.writeLong(task.getTime().getTime());
            }
            else {
                newOut.writeLong(task.getTime().getTime());
            }
        }
    }
    public static void read(TaskList tasks, InputStream in) throws AddTaskException, IOException {
        DataInputStream newIn = new DataInputStream(in);
        int count = newIn.readInt();
        System.out.println(count);
        for(int i=0;i<count;i++) {
            Task task = new Task("t",new Date(1));
            task.setTitle(newIn.readUTF());
            newIn.readInt();
            task.setActive(newIn.readBoolean());
            boolean rep = newIn.readBoolean();
            if(rep ) {
                long interval = newIn.readLong();
                long start = newIn.readLong();
                long end =  newIn.readLong();
                task.setTime(new Date(start),new Date(end),interval);
            } else {
                task.setTime(new Date(newIn.readLong()));
            }
            tasks.addTask(task);
        }
    }
  public static void writeBinary(TaskList tasks, File fileName) throws IOException {
        FileOutputStream newFile = new FileOutputStream(fileName);
        try {
            write(tasks, newFile);
        } finally {
            try {
                newFile.close();
            } catch (IOException e) {
                System.out.println("Couldn't close String");
                Log.error("IOException: Couldn't close String");
            }
        }
    }
    public static void readBinary(TaskList tasks, String fileName) throws IOException, AddTaskException {
        FileInputStream newFile = new FileInputStream(fileName);
        try {
            read(tasks, newFile);
        } finally {
            try {
                newFile.close();
            } catch (IOException e) {
                System.out.println("Couldn't close String");
                Log.error("IOException: Couldn't close String");
            }
        }
    }
    public static void write(TaskList tasks, Writer out) throws IOException {
        PrintWriter newOut = new PrintWriter(new BufferedWriter(out));
        newOut.println(tasks.size());
        for (Task task : tasks) {
            newOut.println(task.getTitle());
            newOut.println(task.getStartTime().getTime());
            newOut.println(task.getTime().getTime());
            newOut.println(task.getRepeatInterval());
            newOut.println(task.isActive());
        }
        newOut.flush();
    }
   public static void read(TaskList tasks, Reader in) throws IOException, AddTaskException {
        BufferedReader newIn = new BufferedReader(in);
        Task task;
        Integer count = Integer.parseInt(newIn.readLine());
        for (Integer i = 0; i < count; i++) {
            String title = newIn.readLine();
            Long start = Long.parseLong(newIn.readLine());
            Long end =Long.parseLong(newIn.readLine());
            Long repeat =Long.parseLong(newIn.readLine());
            Boolean active = Boolean.parseBoolean(newIn.readLine());
            if (new Date(start).compareTo(new Date(end))==0) {
                task=new Task(title,new Date(start));
                task.setActive(active);
                tasks.addTask(task);
            } else {
                task = new Task(title, new Date(start), new Date(end), repeat);
                task.setActive(active);
                tasks.addTask(task);
            }
        }
    }
    public static void textWriter(TaskList tasks, File fileName) throws IOException {
    	SimpleDateFormat dateFormat = new SimpleDateFormat("'['yyyy-MM-dd HH:mm:ss.SSS']'");
        Log.info("Choose date format");
    	try(FileWriter out = new FileWriter(fileName)) {
	    	StringBuffer sb = new StringBuffer();
	    	int counter=0;
	    	for (Task t : tasks) {
                Log.info("Set output format for unrepeated tasks");
		    	sb.append(t.getTitle());
			    	for(int i=0;i<sb.length();i++){
			    	int ii=-1;
			    	ii=sb.indexOf("\"",i);
			    	if(ii!=-1){
				    	sb.replace(ii, ii, "\"\"");
				    	i=ii+1;
			    	}
		    	}
		    	out.write(sb.indexOf("\"")==0?String.valueOf(sb):"\""+String.valueOf(sb)+(sb.lastIndexOf("\"")!=sb.length()-1?"\" ":" "));
		    	sb.delete(0,sb.length());
		    	if(t.isRepeated()){
                    Log.info("Set output format for repeated tasks");
			    	out.write("from " + dateFormat.format(t.getStartTime()) + " to ");
			    	out.write(dateFormat.format(t.getEndTime())+" ");
			    	long second=t.getRepeatInterval()/1000;
			    	long minute=second/60;
			    	long hours=minute/60;
			    	long day=hours/24;

			    	out.write(String.format("every [ " +(day!=0?day+" day ":"") +(hours!=0?hours+" hours ":"")+(minute!=0?minute+" minutes ":"")+(second!=0?second+" seconds ":"")+"]" ));
		    	}
		    	else {
		    		out.write("at "+dateFormat.format(t.getTime()));
		    	}
		    	counter++;
		    	if(!t.isActive()) out.write(" inactive");
		    		out.write(counter!=tasks.size()?";\n":".\n");
	    	}
    	} catch (IOException e){
	    	e.printStackTrace();
            Log.error("IOException");
    	}
    }
    	public static void textReader(TaskList tasks, File fileName) throws IOException, ParseException {
	    	try(BufferedReader FileReader = new BufferedReader(new FileReader(fileName))){
		    	String st;
		    	while ((st = FileReader.readLine()) != null) {
			    	System.out.println(st);
			    	Task task = new Task("t",new Date(455));
			    	int index;
			    	int index2;
			    	if((index=st.indexOf("\" at ["))>=0) {
				    	String name=st.substring(1,index);
				    	task.setTitle(name);
				    	index+=5;
				    	index2=st.indexOf("]",index)+1;
				    	String date=st.substring(index,index2);
				    	task.setTime( (dateFormat.parse(date)));
				    	if(st.indexOf("inactive")>=0){
					    	task.setActive(false);
					    	System.out.println("false");
				    	}
			    	}	else
			    			if((index=st.indexOf("\" from ["))>=0) {
						    	String name=st.substring(1,index);
						    	task.setTitle(name);
						    	index+=7;
						    	index2=st.indexOf("]",index)+1;
						    	String date=st.substring(index,index2);
						    	index=index2+4;
						    	index2=st.indexOf("]",index)+1;
						    	String dateTo=st.substring(index, index2);
						    	if(st.indexOf("inactive")>=0){
							    	task.setActive(false);
							    	System.out.println("false");
						    	}
						    	st=st.substring(st.indexOf("[",index2)+2,st.indexOf("]",index2));
						    	String[] arr = st.split(" ");
						    	long time=0;
						    	for (int i=0;i<arr.length-1;i++){
                                    Log.debug("Set Time values");
							    	if(arr[i+1].equals("seconds")) time+=Long.valueOf(arr[i])*1000;
							    	else
							    		if(arr[i+1].equals("minutes")) time+=Long.valueOf(arr[i])*60*1000;
							    		else
							    			if(arr[i+1].equals("hours")) time+=Long.valueOf(arr[i])*60*60*1000;
							    			else
							    				if(arr[i+1].equals("days")) time+=Long.valueOf(arr[i])*24*60*60*1000;
						    	}
						    	task.setTime( (dateFormat.parse(date)),dateFormat.parse(dateTo),time);
			    	}
			    	tasks.addTask(task);
		    	}
	    	} catch (FileNotFoundException e) {
                Log.error("FileNotFoundException");
		    	e.printStackTrace();
	    	} catch (IOException e) {
                Log.error("IOException");
		    	e.printStackTrace();
	    	} catch (ParseException e) {
                Log.error("ParseException");
		    	e.printStackTrace();
	    	} catch (Exception e) {
                Log.error("Exception");
		    	e.printStackTrace();
	    	}
    }

    public static void writeText(TaskList tasks, String fileName)  throws IOException {
        Log.debug("Writing tasks into a file");
        OutputStream newfileName = new FileOutputStream(fileName);
        DataOutputStream newFile = new DataOutputStream(newfileName);
        newFile.writeInt(tasks.size());
        for (Task task : tasks) {
            newFile.writeUTF(task.getTitle());
            newFile.writeLong(task.getStartTime().getTime());
            newFile.writeLong(task.getEndTime().getTime());
            newFile.writeLong(task.getRepeatInterval());
            newFile.writeBoolean(task.isActive());
        }
    }
    public static void readText(TaskList tasks, String fileName) throws IOException, AddTaskException {
        Log.debug("Reading tasks from  file");
        InputStream newfileName = new FileInputStream(fileName);
        DataInputStream newFile = new DataInputStream(newfileName);
        int count = newFile.readInt();
        for (int i = 0; i < count; i++){
            String title = newFile.readUTF();
            long start =  newFile.readLong();
            long end = newFile.readLong();
            long repeat = newFile.readLong();
            boolean active = newFile.readBoolean();
            Task task = new Task(title, new Date(start), new Date(end), repeat);
            task.setActive(active);
                tasks.addTask(task);
        }
    }

}
