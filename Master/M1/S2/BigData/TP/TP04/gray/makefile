CFLAGS=-O3 -mavx2 -std=gnu99 -mfma
LDFLAGS=-lm
loops=100
prog= gray

test: $(prog)
	./$(prog) $(loops)

$(prog) : $(prog).c
	gcc $(CFLAGS) -o $(prog) $(prog).c $(LDFLAGS)

clean:
	rm -f $(prog)
