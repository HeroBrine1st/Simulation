class Universe():
    'Universe'
    def evolution(**kwargs):
        'Evolution'
        return 'Evolution'

    def physics(**kwargs):
        'laws of physics'
        return 'Laws of Physics'

    def run():
        print('Congratulations, you simulated a universe with %s and the %s.'%(Universe.evolution(),Universe.physics()))

Universe.run()
