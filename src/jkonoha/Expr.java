package jkonoha;

import java.io.PrintStream;
import java.util.*;

public class Expr extends KObject {
	public int ty    = TY.var;
	public int build = TEXPR.UNTYPED;
	public Token tk;
	private boolean flagTerm = false;
	
	//union
	public KObject data;
	public List<Object> cons;
	public Expr single;
	public Block block;
	
	//union
	public Syntax syn;
	public int ivalue;
	public float fvalue;
	public Object ndata;
	public int index;
	public int cid;
	
	public Expr(Syntax syn) {
		this.syn = syn;
	}

	public Expr at(int n) {
		return (Expr)cons.get(n);
	}
	
	public Expr tyCheck(CTX ctx, Object gamma, int reqty, int pol) {
		Expr texpr = this;
		if(this.ty == TY.var) {
			//TODO
		}
		if(this.ty == TY.VOID) {
			return texpr;
		}
		return null;
	}
	
	public void setCons(Object... exprs) {
		if(cons == null) {
			cons = new ArrayList<Object>();
		}
		for (Object expr : exprs) {
			cons.add(expr);
		}
	}
	
	public Expr add(CTX ctx, Expr e) {
		this.cons.add(e);
		return this;
	}
	
	public void setTerm(boolean b) {
		this.flagTerm = b;
	}
	
	public boolean isTerm() {
		return flagTerm;
	}
	
	public void dump(PrintStream out, int n, int nest) {
		if (nest == 0) out.println();
		Token.dumpIndent(out, nest);
		if (this == null) {
			out.println ("[" + n + "] ExprTerm: null");
		} else if (this.isTerm()) {
			out.println ("[" + n + "] ExprTerm: kw = " + tk.kw + ", " + this.tk);
			if(this.ty != TY.VAR) {
				
			}
		} else {
			int i = 0;
			int size = cons != null ? cons.size() : 0;
			if (this.syn == null) {
				out.println ("[" + n + "] Cons: kw = null, size =" + size);
			} else { 
				out.println ("[" + n + "] Conshoge: kw='" + syn.kw + "', size = " + size);
			}
			if (this.ty != TY.var) {
				
			}
			for (; i < size; i++) {
				Object o = this.cons.get(i);
				if(o instanceof Expr) {
					((Expr)o).dump(out, i, nest+1);
				} else {
					Token.dumpIndent(out, nest+1);
					if (o instanceof Token) {
						Token tk = (Token)o;
						out.print("[" + i + "]: " + o.getClass().getName() + " ");
						tk.dump(out);
					} else if (o == null) {
						out.println("[" + i + "] O: null");
					} else {
						out.println("[" + i + "] O: " + o.getClass().getName());
					}
				}
			}
		}
	}
}
class ConstExpr extends Expr {  // as if NConstExpr 
	//public final Object data;

	public ConstExpr(Syntax syn, KObject data) {
		super(syn);
		this.data = data;
	}

//int CONST    =  0;
//int NCONST   =  3;  if ty is unboxed
}
class ConsExpr extends Expr {
	
	public ConsExpr(Syntax syn) {
		super(syn);
	}
	
//	public List<Expr> cons = new ArrayList<Expr>();
//	
//	@Override public Expr at(int n) {
//		return cons.get(n);
//	}
//	
//	@Override public List<Expr> getCons() {
//		return cons;
//	}
//	
////	int CALL     =  9;
////	int AND      = 10;
////	int OR       = 11;
////	int LET      = 12;
}
//public abstract class Expr extends KObject {
//	public int ty;     // TY_var ==> TY_int
//	public int build;  // 
//	public Token tk;   // unfamiliar 
//	
//	public Object getData() {
//		throw new RuntimeException();
//	}
//	
//	public Expr at(int n) {
//		throw new RuntimeException();
//	}
//	
//	public List<Expr> getCons() {
//		throw new RuntimeException();
//	}
//	
//	public int getIndex() {
//		throw new RuntimeException();
//	}
//	
//	public Expr tyCheck(CTX ctx, Object gamma, int reqty, int pol) {
//		//TODO
//		return null;
//	}
//	
//	public void typed(int build, int ty) {
//		this.build = build;
//		this.ty = ty;
//	}
//	
//}
//
//class ConsExpr extends Expr {
//	
//	public List<Expr> cons = new ArrayList<Expr>();
//	
//	@Override public Expr at(int n) {
//		return cons.get(n);
//	}
//	
//	@Override public List<Expr> getCons() {
//		return cons;
//	}
//	
////	int CALL     =  9;
////	int AND      = 10;
////	int OR       = 11;
////	int LET      = 12;
//}
//
//class TermExpr extends Expr {
//	
//}
//
//class ConstExpr extends Expr {  // as if NConstExpr 
//	public final Object data;
//	
//	public ConstExpr(Object data) {
//		this.data = data;
//	}
//	
//	@Override public Object getData() {
//		return data;
//	}
//	
////	int CONST    =  0;
////	int NCONST   =  3;  if ty is unboxed
//}
//
////new P("name");
////(new newExpr(P) name)
//
//class NewExpr extends Expr {
//	//int NEW      =  1;
//}
//
//class NullExpr extends Expr {
//	//int NULL     =  2;	
//}
//
//// local variable (block), (function) 
//
//class FuncScopeVariableExpr extends Expr {
//	public int index;
//	
//	@Override public int getIndex() {
//		return index;
//	}
//	//int LOCAL    =  4;
//}
//
//class BlockScopeVariableExpr extends Expr {
//	public int index;
//	
//	@Override public int getIndex() {
//		return index;
//	}
//	
//	//int LOCAL_   = -4;   /*THIS IS NEVER PASSED*/
//	//int BLOCK_   = -3;   /*THIS IS NEVER PASSED*/
//	//int FIELD_   = -2;   /*THIS IS NEVER PASSED*/
//}
//
//class FieldExpr extends Expr {
//	public int index;
//	public int xindex;
//	//int FIELD    =  6;
//}
//
//class BoxingExpr extends Expr {
//	public Expr single;
////	int BOX      =  7;
////	int UNBOX    =  8;
//}
//
//class BlockExpr extends Expr {
//	public Block block;
////	int BLOCK    =  5;
//}
//
//class StackTopExpr extends Expr {
//	//TODO;
//}
