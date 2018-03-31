package com.genuinemark.qrapp;


/*
public class ResultAdapter extends RecyclerView.Adapter<ResultAdapter.MyViewAdapter> {


    Context context;

    List<SimilarProducts>list = new ArrayList<>();

    public ResultAdapter(Context context ,List<SimilarProducts>list ) {

        this.context = context;
        this.list = list;

    }

    @NonNull
    @Override
    public MyViewAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.grid1_list_model, parent, false);
        return new MyViewAdapter(view);

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewAdapter holder, int position) {

        SimilarProducts item = list.get(position);

        holder.title.setText(item.getProductImage());
        holder.brand.setText(item.getBrandName());
        holder.quality.setText(item.getQuality());
        holder.ratingBar.setRating((Float) item.getRating());
        holder.like.setText(item.getRecommendation());


        DisplayImageOptions options = new DisplayImageOptions.Builder().
                cacheInMemory(true).cacheOnDisk(true).resetViewBeforeLoading(false).build();

        ImageLoader loader = ImageLoader.getInstance();
        loader.displayImage(item.getProductImage() , holder.image , options);




    }

    public void setgrid(List<SimilarProducts>list){

        this.list = list;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewAdapter extends RecyclerView.ViewHolder {

        TextView title , brand , quality , like;

        ImageView image;

        RatingBar ratingBar;

        public MyViewAdapter(View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.title);

            brand = itemView.findViewById(R.id.brand);

            quality = itemView.findViewById(R.id.quality);

            like = itemView.findViewById(R.id.like);

            image = itemView.findViewById(R.id.image);

            ratingBar = itemView.findViewById(R.id.points);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Intent intent = new Intent(context, productDetails.class);
                    context.startActivity(intent);

                }
            });

        }
    }
}
*/
