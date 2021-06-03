public class Hora {
    private int hora;
    private int min;
    private int seg;
    private int milis;

    public Hora() {
    }

    public Hora(int hora, int min, int seg, int milis){
        this.hora = hora;
        this.min = min;
        this.seg = seg;
        this.milis = milis;
    }

    public int getHora(){
        return this.hora;
    }
    public void setHora(int hora){
        this.hora = hora;
    }

    public int getMin() {
        return min;
    }
    public void setMin(int min) {
        this.min = min;
    }

    public int getSeg() {
        return seg;
    }
    public void setSeg(int seg) {
        this.seg = seg;
    }

    public int getMilis() {
        return milis;
    }
    public void setMilis(int milis) {
        this.milis = milis;
    }

    @Override
    public String toString() {
        return hora + " : " + min + " : " + seg + " , " + milis;
    }

    public void acrescentar(int tempo, int unidade){
        switch (unidade) {
            case Unidades.HORA:
                int somaHora = tempo%24;
                this.hora = (this.hora + somaHora)%24;
                break;
            case Unidades.MINUTO:
                this.hora = (this.hora + (tempo+this.min)/60)%24;
                int somaMin = tempo%60;
                this.min = (this.min + somaMin)%60;
                break;
            case Unidades.SEGUNDO:
                this.hora = (this.hora + (this.min+(tempo+this.seg)/60)/60)%24;
                this.min = (this.min + (tempo+this.seg)/60)%60;
                int somaSeg = tempo%60;
                this.seg = (this.seg + somaSeg)%60;
                break;
            case Unidades.MILISSEGUNDO:
                this.hora = (this.hora + (this.min + (this.seg + (tempo+this.milis)/1000)/60)/60)%24;
                this.min = (this.min + (this.seg+(tempo+this.milis)/1000)/60)%60;
                this.seg = (this.seg + (tempo+this.milis)/1000)%60;
                int somaMilissegundo = tempo%1000;
                this.milis = (this.milis + somaMilissegundo)%1000;
                break;
        }
    }

    public void diminuir(int tempo, int unidade){
        switch (unidade) {
            case Unidades.HORA:
                int subHora = tempo%24;
                if (this.hora < subHora){
                    this.hora = 24 + (this.hora - subHora);
                } else {
                    this.hora = this.hora - subHora;
                }
                break;

            case Unidades.MINUTO:
                int subMin = tempo%60;
                if (this.min <= subMin){
                    this.min = 60 + (this.min - subMin);
                    diminuir((int)Math.ceil((float) tempo/60), Unidades.HORA);
                    if(tempo%60 == 0){
                        this.min = 0;
                    }
                }
                else {
                    this.min = this.min - subMin;
                    if (this.min != 0 && tempo > 0){
                        diminuir(tempo/60, Unidades.HORA);
                    } else {
                        diminuir((int)Math.ceil((float) tempo/60), Unidades.HORA);
                    }
                }
                break;

            case Unidades.SEGUNDO:
                int subSeg = tempo%60;
                if (this.seg <= subSeg) {
                    if (tempo >= 60 && this.seg == 0) {
                        this.seg = 60 + (this.seg - subSeg);
                        diminuir((int)Math.ceil((float) tempo/60), Unidades.MINUTO);
                        if(tempo%60 == 0){
                            this.seg = 0;
                        }
                    } else {
                        this.seg = 60 + (this.seg - subSeg);
                        diminuir((int)Math.ceil((float) tempo/60), Unidades.MINUTO);
                    }
                }
                else {
                    this.seg = this.seg - subSeg;
                    if (this.seg != 0 && tempo > 0){
                        diminuir(tempo/60, Unidades.MINUTO);
                    } else {
                        diminuir((int)Math.ceil((float) tempo/60), Unidades.MINUTO);
                    }
                }
                break;
            case Unidades.MILISSEGUNDO:
                int subMilissegundo = tempo%1000;
                if (this.milis <= subMilissegundo) {
                    if (tempo >= 1000 && this.milis == 0) {
                        this.milis = 1000 + (this.milis - subMilissegundo);
                        diminuir((int)Math.ceil((float) tempo/1000), Unidades.SEGUNDO);
                        if(tempo%1000 == 0){
                            this.milis = 0;
                        }
                    } else {
                        this.milis = this.milis - subMilissegundo;
                        diminuir(tempo/1000, Unidades.SEGUNDO);
                    }
                } else {
                    this.milis = this.milis - subMilissegundo;
                    diminuir((int)Math.ceil((float) tempo/1000), Unidades.SEGUNDO);
                }
                break;
        }
    }
}
