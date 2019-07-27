import sys
import subprocess
import pdfkit
inputfile = r"E:\git_sunliangliang9201\jupyterfiles\homeWork.iptnb"
temp_html = inputfile[0:inputfile.rfind('.')]+'.html'
command = 'ipython nbconvert --to html ' + inputfile
subprocess.call(command,shell=True)
output_file =inputfile[0:inputfile.rfind('.')]+'.pdf'
print(1)
pdfkit.from_file(temp_html,output_file)
#subprocess.call('rm '+temp_html,shell=True)