#ifndef FILE_HEADER

    #define FILE_HEADER
    typedef struct t_file_body {
        struct t_file *prev;
        struct t_file *next;
        int label;
    }t_file_body;

    typedef struct t_file_head {
        struct t_file *first;
    }t_file_head;

    #ifndef FILE
        #define EXTERN extern
    #else
        #define EXTERN
    #endif

    EXTERN t_file_head *createFile();
    EXTERN t_file_body *newElement(int labelElement);
    EXTERN void thread(t_file_body *file_body, t_file_head *file_head);
    EXTERN int scroll(t_file_head *file_head);
    EXTERN int isEmpty(t_file_head *file_head);

#endif
