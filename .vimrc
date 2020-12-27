set nocompatible
filetype off
syntax on
filetype indent plugin on
set shiftwidth=4
set softtabstop=4
set expandtab
set number
set background=dark
set wildmenu
set incsearch
autocmd BufWritePre *.[ch] :%s/\s\+$//e
