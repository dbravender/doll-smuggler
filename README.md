# doll-smuggler

Finds optimal value packing given a set of dolls and a maximum weight
as described below.

## Usage

    $ lein run [FILENAME]
    $ lein run < FILENAME

## Testing

    $ lein test

## Accepted input

Input is required to be in the following format:

    max weight: 400           # First line must contain max weight
                              # ignored but there must be a line here
    available dolls:          # ignored but there must be a line here
                              # ignored but there must be a line here
    name    weight value      # ignored but there must be a line here
    luke        9   150       # at least one doll is required
    ...                       #   * name must not contain spaces

## Output sample

    packed dolls:

    name    weight value
    sally       4    50
    eddie       7    20
    grumpy     22    80
    dusty      43    75
    grumpkin   42    70
    marc       11    70
    randal     27    60
    puppy      15    60
    dorothy    50   160
    candice   153   200
    anthony    13    35
    luke        9   150
