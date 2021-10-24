#include <dirent.h> 
#include <stdio.h> 
#include <sys/stat.h>
#include <string.h>
#include <stdlib.h>
#include <unistd.h>

int loops = 0;

void show_dir_content(char * dirpath)
{
  DIR * directory = opendir(dirpath);

  if(directory!=NULL) 
  {
    struct dirent * drnt;
    struct stat filestat;

      while ((drnt = readdir(directory)) != NULL)
        {
          if(drnt-> d_type != DT_DIR)
            {
               char fullpath[999];

               strcpy(fullpath,dirpath);
               strcat(fullpath,"/");
               strcat(fullpath,drnt->d_name);

               int rc = stat(fullpath,&filestat);

               if (rc != 0)
               {
                perror("Couldn't open the file\n");
               }

                else
                {
                  printf("In:%s %s%s %ld\n\n",dirpath,"File: ", drnt->d_name,filestat.st_size);//drnt->d_reclen
                  loops++;
                }
                
            }

          else
            {

              if(strcmp(drnt->d_name,"..")!=0 
                            && 
                 strcmp(drnt->d_name,".")!=0 
                            && 
                 drnt -> d_type == DT_DIR ) 

                {
                  char *d_path = malloc (strlen(dirpath) + strlen(drnt->d_name) + 2);

                  sprintf(d_path, "%s/%s", dirpath, drnt->d_name);
                 
                  show_dir_content(d_path);

                  free (d_path);
                }
          }
        }

      closedir(directory);
      
  }
}

int main(int argc, char *argv[])
{
  char cwd[256];
  getcwd(cwd,sizeof(cwd));

  if (argv[1])
    {
      show_dir_content(argv[1]);
      
      if (loops == 0)
      
        printf("Input a correct directory or don't give an argument\n");
    }

  else

    show_dir_content(cwd);
}
